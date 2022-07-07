package com.example.swagger_restdocs.controller;

import com.example.swagger_restdocs.dto.TestDto;
import com.example.swagger_restdocs.service.TestUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static com.example.swagger_restdocs.ApiDocumentUtils.getDocumentRequest;
import static com.example.swagger_restdocs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    private TestDto getUserDto(String hello_MockTest) {
        TestDto testDto = new TestDto();
        testDto.setStatus(true);
        testDto.setId(1);
        testDto.setCreateAt(LocalDateTime.now());
        testDto.setName(hello_MockTest);
        return testDto;
    }

    @Test
    public void mockMvcTestCreate() throws Exception {
        TestDto testDto = getUserDto("MockTest");

        given(userService.createUserDto(testDto)).willReturn(testDto);

        String content = objectMapper.writeValueAsString(testDto);

        ResultActions perform = mockMvc.perform(post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(testDto.getName()))
                .andDo(print());
    }

    @Test
    public void mockMvcTestFind() throws Exception {
        TestDto testDto = getUserDto("MockTest");

        given(userService.createUserDto(testDto)).willReturn(testDto);
        when(userService.findUserDto(testDto.getId())).thenReturn(testDto);

        ResultActions perform = mockMvc.perform(get("/userInformation/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));


        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(testDto.getName()))
                .andDo(print());
    }

    @Test
    public void restDocs() throws Exception {
        TestDto testDto = getUserDto("hello MockTest");

        given(userService.createUserDto(testDto)).willReturn(testDto);
//        when(userService.findUserDto(userDto.getId())).thenReturn(userDto);

        String content = objectMapper.writeValueAsString(testDto);

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
                                fieldWithPath("createAt").type(JsonFieldType.STRING).attributes(getDateFormat())
                                        .description("시간")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("ID"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("status").type(JsonFieldType.BOOLEAN).description("상태값"),
                                fieldWithPath("createAt").type(JsonFieldType.STRING).attributes(getDateFormat())
                                        .description("시간")
                        )));
    }

    private Attributes.Attribute getDateFormat() {
        return key("format").value("yyyy-MM-dd");
    }
}
