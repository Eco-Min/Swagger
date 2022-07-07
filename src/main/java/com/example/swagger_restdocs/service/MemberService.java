package com.example.swagger_restdocs.service;

import com.example.swagger_restdocs.domain.entity.Member;
import com.example.swagger_restdocs.domain.entity.Team;
import com.example.swagger_restdocs.rapository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void checkFetchInMemberService(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        System.out.println("=======================");
        Team team = member.getTeam();
        System.out.println("===========================");
        System.out.println("team.getId() = " + team.getId());
        System.out.println("team.getName() = " + team.getName());
        System.out.println("===========================");
        List<Member> members = team.getMembers();
    }

    public Member findByUserName(String memberName) {
        return memberRepository.findByUsername(memberName);
    }
}
