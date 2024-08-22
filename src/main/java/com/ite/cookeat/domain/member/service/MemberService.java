package com.ite.cookeat.domain.member.service;

import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;

public interface MemberService {
    GetUserDetailsRes findUserDetailsByUsername(String username);
}
