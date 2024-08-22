package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PostLoginRes {
    private String username;
    private String nickname;
    private String profileImage;
    private String accessToken;
}
