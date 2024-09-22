package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.admin.dto.GetTopSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetTotalSskcookDetailsRes;
import com.ite.cookeat.domain.sskcook.dto.PostLikesReq;
import com.ite.cookeat.global.dto.PaginatedRes;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * 슥쿡 등록 및 조회, 상세 기능(좋아요, 저장, 신고) 등을 처리하는 Service
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.08.19
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.19    박유진       최초 생성
 * 2024.08.23    박유진       냉장고 속 슥쿡 추천
 * 2024.08.24    박유진       슥쿡 삭제 및 등록
 * 2024.08.27    박유진       슥쿡 수정
 * </pre>
 */
public interface SskcookService {

  GetTotalSskcookDetailsRes findSskcookTotalDetails(Integer sskcookId);

  List<GetFridgeRecipeRes> findMyFridgeRecipe();

  Integer addSskcook(String request, MultipartFile file);

  PaginatedRes<GetSearchSskcookRes> findSearchRecentSskcookList(String keyword, Integer page);

  PaginatedRes<GetSearchSskcookRes> findSearchLikesSskcookList(String keyword, Integer page);

  PaginatedRes<GetSearchSskcookRes> findRecentSskcookList(Integer page);

  PaginatedRes<GetSearchSskcookRes> findMonthlySskcookList(String date, Integer page);

  PaginatedRes<GetSearchSskcookRes> findUserSskcookList(String username, Integer page);

  PaginatedRes<GetSearchSskcookRes> findTagSskcookList(String tag, Integer page);

  void modifySskcookDeletedate(Integer sskcookId);

  void addLikes(PostLikesReq req);

  void removeLikes(PostLikesReq req);

  Integer findLikes(PostLikesReq req);

  void addReport(PostLikesReq postLikesReq);

  void removeReport(PostLikesReq postLikesReq);

  Integer findReport(PostLikesReq postLikesReq);

  Integer modifySskcook(String request, MultipartFile file);

  List<GetTopSskcookRes> findTopSskcookList(String yearMonth);
}
