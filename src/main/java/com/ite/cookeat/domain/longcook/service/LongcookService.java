package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.ite.cookeat.domain.sskcook.dto.GetLongcookPageRes;
import com.ite.cookeat.global.dto.PaginatedRes;

public interface LongcookService {

  GetLongcookRes findLongcook(Integer longcookId);

  PaginatedRes<GetLongcookRes> findLongcookList(String username, Integer page);

  PaginatedRes<GetLongcookRes> findRecentLongcookList(Integer page);

  Integer modifyLongcookDeletedate(Integer longcookId);

  Integer addLongcook(String request, MultipartFile file);
}
