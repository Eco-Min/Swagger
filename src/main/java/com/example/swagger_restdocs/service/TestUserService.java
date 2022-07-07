package com.example.swagger_restdocs.service;

import com.example.swagger_restdocs.dto.TestDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestUserService {
    private static final Map<Integer, TestDto> USER_DTO_MAP = new HashMap<>();

    public TestDto createUserDto(TestDto testDto) {
        if(USER_DTO_MAP.containsKey(testDto.getId())){
            throw new IllegalArgumentException("중복 Id");
        }
        USER_DTO_MAP.put(testDto.getId(), testDto);
        return testDto;
    }

    public TestDto findUserDto(Integer id) {
        return USER_DTO_MAP.get(id);
    }
}
