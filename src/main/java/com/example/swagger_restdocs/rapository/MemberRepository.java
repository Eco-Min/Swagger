package com.example.swagger_restdocs.rapository;

import com.example.swagger_restdocs.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);
}
