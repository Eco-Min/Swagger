package com.example.swagger_restdocs.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {
    @ApiModelProperty(example = "유저 아이디")
    private Integer id;
    @ApiModelProperty(example = "유저 이름")
    private String name;
    private boolean status;
    private LocalDateTime createAt;
}
