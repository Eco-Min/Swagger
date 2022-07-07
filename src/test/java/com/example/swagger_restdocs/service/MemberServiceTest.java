package com.example.swagger_restdocs.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("findByMemberId")
    @Transactional
    public void findByMemberId() throws Exception{
        memberService.checkFetchInMemberService(5L);
    }

}