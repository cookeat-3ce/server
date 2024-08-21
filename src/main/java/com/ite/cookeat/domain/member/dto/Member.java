package com.ite.cookeat.domain.member.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
public class Member {

    // 고유번호
    private Integer memberId;
    // 아이디
    private String username;
    // 비밀번호(encrypted)
    private String password;

    private String nickname;

    private String profileImage;

    // 권한 목록(문자열)
    private String roles;


    // 유저의 역할(권한) 문자열에서 리스트 변환
    public List<String> getRoleList() {
        if (this.roles != null && this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList();
    }

}