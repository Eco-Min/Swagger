package com.example.swagger_restdocs.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @ApiOperation(value = "user", notes = "user 정보")
    @GetMapping(value = "/user")
    public UserDto helloUser(){
        UserDto userDto = new UserDto();
        userDto.setId(5);
        userDto.setName("hello");
        userDto.setStatus(true);
        userDto.setCreateAt(LocalDateTime.now());
        return userDto;
    }


    @ApiOperation(value = "paramSample", notes = "테스트입니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "page not found!")
    })
    @GetMapping(value = "/paramSample")
    public Map<String, String> selectBoard(@ApiParam(value = "샘플번호", required = true, example = "1")
                                           @RequestParam String no) {

        Map<String, String> result = new HashMap<>();
        result.put(no, "테스트");
        result.put("test contents", "테스트 내용");
        return  result;
    }
}