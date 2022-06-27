package com.example.swagger_restdocs.controller;

import com.example.swagger_restdocs.service.TestUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.example.swagger_restdocs.ApiDocumentUtils.getDocumentRequest;
import static com.example.swagger_restdocs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest(TestController.class)
@AutoConfigureRestDocs
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TestUserService userService;

    @Test
    public void mockMvcTestCreate() throws Exception {
        UserDto userDto = getUserDto("MockTest");

        given(userService.createUserDto(userDto)).willReturn(userDto);

        String content = objectMapper.writeValueAsString(userDto);

        ResultActions perform = mockMvc.perform(post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(userDto.getName()))
                .andDo(print());
    }

    @Test
    public void mockMvcTestFind() throws Exception {
        UserDto userDto = getUserDto("MockTest");

        given(userService.createUserDto(userDto)).willReturn(userDto);
        when(userService.findUserDto(userDto.getId())).thenReturn(userDto);

        ResultActions perform = mockMvc.perform(get("/userInformation/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));


        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(userDto.getName()))
                .andDo(print());
    }

    @Test
    public void restDocs() throws Exception {
        UserDto userDto = getUserDto("hello MockTest");

        given(userService.createUserDto(userDto)).willReturn(userDto);
//        when(userService.findUserDto(userDto.getId())).thenReturn(userDto);

        String content = objectMapper.writeValueAsString(userDto);

        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andDo(document("index",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("ID"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("status").type(JsonFieldType.BOOLEAN).description("상태값"),
                                fieldWithPath("createAt").type(JsonFieldType.STRING).description("시간")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("ID"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("status").type(JsonFieldType.BOOLEAN).description("상태값"),
                                fieldWithPath("createAt").type(JsonFieldType.STRING).description("시간")
                        )));
    }

    private Attributes.Attribute getDateFormat() {
        return key("format").value("yyyy-MM-dd");
    }

    private UserDto getUserDto(String hello_MockTest) {
        UserDto userDto = new UserDto();
        userDto.setStatus(true);
        userDto.setId(1);
        userDto.setCreateAt(LocalDateTime.now());
        userDto.setName(hello_MockTest);
        return userDto;
    }
}
