package com.example.swagger_restdocs.service;

import com.example.swagger_restdocs.controller.UserDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestUserService {
    private static final Map<Integer, UserDto> USER_DTO_MAP = new HashMap<>();

    public UserDto createUserDto(UserDto userDto) {
        if(USER_DTO_MAP.containsKey(userDto.getId())){
            throw new IllegalArgumentException("중복 Id");
        }
        USER_DTO_MAP.put(userDto.getId(), userDto);
        return userDto;
    }

    public UserDto findUserDto(Integer id) {
        return USER_DTO_MAP.get(id);
    }
}
