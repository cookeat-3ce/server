package com.ite.cookeat.domain.live.service;

import static com.ite.cookeat.exception.ErrorCode.FILE_UPLOAD_FAIL;
import static com.ite.cookeat.exception.ErrorCode.LIVE_NOT_FOUND;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ite.cookeat.domain.live.dto.GetLiveRes;
import com.ite.cookeat.domain.live.dto.PostLiveReq;
import com.ite.cookeat.domain.live.mapper.LiveMapper;
import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import com.ite.cookeat.s3.service.S3UploadService;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 김지수
 * @version 1.0
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.19    박유진       최초 생성
 * 2024.08.23    김지수       라이브 등록
 * 2024.08.25    박유진       진행 중인 라이브 목록 조회
 * 2024.09.04    김지수       라이브 종료, 상세 조회
 * </pre>
 * @since 2024.08.19
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LiveServiceImpl implements LiveService {

  private static final int PAGE_SIZE = 9;

  private final LiveMapper liveMapper;
  private final MemberService memberService;
  private final S3UploadService s3UploadService;
  private final ObjectMapper objectMapper;

  /**
   * 실시간 요리 클래스(라이브) 등록
   *
   * @param req (라이브 등록 시 필요한 정보와 등록한 사용자 정보)
   * @return 1 이상이면 라이브 등록 요청 처리 성공
   */
  @Override
  @Transactional
  public Integer saveLive(String req, MultipartFile file) {
    String thumbnailUrl = null;
    PostLiveReq postLiveReq = null;
    try {
      postLiveReq = objectMapper.readValue(req, PostLiveReq.class);
      thumbnailUrl = s3UploadService.saveFile(file);
    } catch (IOException e) {
      throw new CustomException(FILE_UPLOAD_FAIL);
    }
    postLiveReq.setThumbnail(thumbnailUrl);
    postLiveReq.setMemberId(memberService.findMemberId(postLiveReq.getUsername()));
    liveMapper.insertLive(postLiveReq);
    return postLiveReq.getLiveId();
  }

  @Override
  @Transactional(readOnly = true)
  public PaginatedRes<GetLiveRes> findLiveList(String keyword, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(PAGE_SIZE)
        .pageNum(page)
        .build();

    return PaginatedRes.<GetLiveRes>builder()
        .cri(cri)
        .total(liveMapper.selectLiveCount(keyword))
        .data(liveMapper.selectLiveListByKeyword(cri, keyword))
        .build();
  }

  /**
   * 라이브 종료
   *
   * @param liveId (라이브 ID)
   */
  @Override
  @Transactional
  public void modifyLiveEnddate(Integer liveId) {
    Integer result = liveMapper.updateLiveEnddate(liveId);
    if (result <= 0) {
      throw new CustomException(LIVE_NOT_FOUND);
    }
  }

  /**
   * 라이브 상세 조회
   *
   * @param sessionId (openvidu에서 제공하는 sessionId)
   * @return 라이브 상세 데이터
   */
  @Override
  @Transactional(readOnly = true)
  public GetLiveRes findLiveDetail(String sessionId) {
    Optional<GetLiveRes> result = liveMapper.selectLiveDetail(sessionId);
    return result.orElseThrow(() -> new CustomException(LIVE_NOT_FOUND));
  }
}
