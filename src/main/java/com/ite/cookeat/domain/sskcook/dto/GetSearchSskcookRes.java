package com.ite.cookeat.domain.sskcook.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 검색한 슥쿡 정보를 받기 위한 DTO
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.22
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.22    양재혁       최초 생성
 * </pre>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetSearchSskcookRes {

  private Integer sskcookId;
  private String nickname;
  private String sskcookUrl;
  private String title;
  private String profileImage;
  private String username;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date regdate;
  private Integer countLikes;
}
