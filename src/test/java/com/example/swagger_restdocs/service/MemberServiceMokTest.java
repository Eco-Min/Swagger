package com.example.swagger_restdocs.service;

import com.example.swagger_restdocs.domain.entity.Member;
import com.example.swagger_restdocs.rapository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceMokTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("findByUserName_Repository")
    public void repositoryTest() throws Exception{
        //given
        Member member = new Member("HelloM1");

        //when
        given(memberRepository.findByUsername(member.getUsername())).willReturn(member);

        //then
        Member byUsername = memberRepository.findByUsername(member.getUsername());
        assertThat(byUsername.getUsername()).isEqualTo(member.getUsername());

    }

    @Test
    @DisplayName("findByUserName")
    public void findByUserName() throws Exception{
        Member member = new Member("HelloM1");
        given(memberService.findByUserName(member.getUsername())).willReturn(member);

        Member byUsername = memberService.findByUserName(member.getUsername());
        assertThat(byUsername.getUsername()).isEqualTo(member.getUsername());
    }
}