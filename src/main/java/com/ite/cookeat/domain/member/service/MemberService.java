package com.ite.cookeat.domain.member.service;

import com.ite.cookeat.domain.member.dto.PostLoginReq;
import com.ite.cookeat.domain.member.dto.PostLoginRes;
import com.ite.cookeat.domain.member.dto.PostSignUpReq;

public interface MemberService {
    void addMember(PostSignUpReq postSignUpReq);
    PostLoginRes login(PostLoginReq postLoginReq);
}
