package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.ite.cookeat.domain.sskcook.dto.GetLongcookPageRes;

public interface LongcookService {

  GetLongcookRes findLongcook(Integer longcookId);

  GetLongcookPageRes findLongcookList(String username, Integer page);

  GetLongcookPageRes findRecentLongcookList(Integer page);

  Integer modifyLongcookDeletedate(Integer longcookId);

  Integer addLongcook(String request, MultipartFile file);
}
