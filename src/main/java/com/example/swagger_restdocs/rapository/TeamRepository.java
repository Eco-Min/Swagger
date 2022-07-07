package com.example.swagger_restdocs.rapository;

import com.example.swagger_restdocs.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
