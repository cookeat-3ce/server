package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostSignUpReq {
    private String username;
    private String password;
    private String profileImage;
    private String nickname;
}
