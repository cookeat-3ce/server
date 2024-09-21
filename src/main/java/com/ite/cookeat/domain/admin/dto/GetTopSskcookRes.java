package com.ite.cookeat.domain.admin.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 신고된 슥쿡 영상 BLOCKED 처리를 위한 DTO
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.09.07
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.09.07    김지수       최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetTopSskcookRes {

  private Integer sskcookId;
  private Integer likeCounts;
  private String yearMonth;
  private String profileImage;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date regdate;
  private String sskcookUrl;
  private String title;
  private String nickname;
  private String username;
}
