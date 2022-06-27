package com.example.swagger_restdocs.controller;

import com.example.swagger_restdocs.service.TestUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private TestUserService userService;

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

    @PostMapping(value = "/user")
    public UserDto helloUser(@RequestBody UserDto userDto){
        return userService.createUserDto(userDto);
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

    @GetMapping(value = "/userInformation/{id}")
    public UserDto userInformation(@PathVariable Integer id){
        return userService.findUserDto(id);
    }
}