package com.ite.cookeat.domain.admin.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 이벤트 등록 DTO
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.26
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.26    김지수       최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostEventReq {

  @Setter
  private Integer eventId;
  private String title;
  private String content;
  private Date startdate;
  private Date enddate;
  private String thumbnail;
}
