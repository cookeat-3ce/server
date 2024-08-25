package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongcookReq;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface LongcookService {

  GetLongcookRes findLongcook(Integer longcookId);

  List<GetLongcookRes> findLongcookList(GetLongcookReq getLongcookReq);

  Integer modifyLongcookDeletedate(Integer longcookId);

  Integer addLongcook(String request, MultipartFile file);
}
