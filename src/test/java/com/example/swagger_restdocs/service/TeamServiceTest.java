package com.example.swagger_restdocs.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    @DisplayName("findByTeamId")
    @Transactional
    public void findByTeamId() throws Exception{
        teamService.checkFetchInTeamService(2L);
     }
}