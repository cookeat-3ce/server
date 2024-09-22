package com.ite.cookeat.domain.event.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이벤트 상세 조회 DTO
 *
 * @author 김지수
 * @version 1.0
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.25    김지수       최초 생성
 * </pre>
 * @since 2024.08.25
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetEventDetailRes {

  private Integer eventId;
  private String title;
  private String content;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date startdate;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date enddate;
  private String thumbnail;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date regdate;
}
