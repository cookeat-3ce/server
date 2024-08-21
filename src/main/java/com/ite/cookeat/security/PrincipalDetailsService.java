package com.ite.cookeat.security;


import java.util.Optional;

import com.ite.cookeat.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ite.cookeat.domain.member.dto.Member;
import com.ite.cookeat.domain.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

import static com.ite.cookeat.exception.ErrorCode.MEMBER_NOT_FOUND;


@Service
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optional_member = memberMapper.selectUsername(username);
        log.info("[PrincipalDetailsService] loadUserByUsername {" + optional_member.get() + "}");
        log.info("principal details: " + new PrincipalDetails(optional_member.get()));

        if (optional_member.isEmpty()) throw new CustomException(MEMBER_NOT_FOUND);

        return new PrincipalDetails(optional_member.get());
    }
}