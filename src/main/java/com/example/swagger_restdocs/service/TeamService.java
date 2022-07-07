package com.example.swagger_restdocs.service;

import com.example.swagger_restdocs.domain.entity.Member;
import com.example.swagger_restdocs.domain.entity.Team;
import com.example.swagger_restdocs.rapository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public void checkFetchInTeamService(Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        System.out.println("==============================");
        List<Member> members = team.getMembers();
        for (Member member : members) {
            System.out.println("member = " + member);
        }
    }
}
