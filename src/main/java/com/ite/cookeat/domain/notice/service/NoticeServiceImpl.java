package com.ite.cookeat.domain.notice.service;

import static com.ite.cookeat.exception.ErrorCode.NOTICE_NOT_FOUND;

import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.domain.notice.dto.PostNoticeReq;
import com.ite.cookeat.domain.notice.mapper.NoticeMapper;
import com.ite.cookeat.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 김지수
 * @version 1.0
 * @since 2024.08.23
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    김지수       최초 생성, 공지 등록, 삭제
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

  private final NoticeMapper noticeMapper;
  private final MemberService memberService;

  /**
   * 사용자 공지 삭제
   *
   * @param noticeId (공지 ID)
   * @return 1 이상이면 삭제 요청 성공
   */
  @Override
  public Integer modifyNoticeDeletedate(Integer noticeId) {
    Integer result = noticeMapper.updateNoticeDeletedate(noticeId);
    if (result <= 0) {
      throw new CustomException(NOTICE_NOT_FOUND);
    }
    return noticeId;
  }

  /**
   * 사용자 공지 등록
   *
   * @param req (등록할 공지 데이터)
   * @return 1 이상이면 등록 요청 성공
   */
  @Override
  public Integer addNotice(PostNoticeReq req) {
    req.setMemberId(memberService.findMemberId(req.getUsername()));
    noticeMapper.insertNotice(req);
    return req.getNoticeId();
  }
}
