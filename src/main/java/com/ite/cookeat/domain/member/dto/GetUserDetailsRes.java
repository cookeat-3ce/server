package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetUserDetailsRes {
    private String nickname;
    private String profileImage;
    private String oneLiner;
    private Integer subscriptionCount;
    private Integer sskcookCount;
}
