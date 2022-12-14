/*                                                                                                                                                                                            package com.codestates.preproject.controller;

import com.codestates.preproject.member.controller.MemberController;
import com.codestates.preproject.member.dto.MemberDto;
import com.codestates.preproject.member.entity.Member;
import com.codestates.preproject.member.mapper.MemberMapper;
import com.codestates.preproject.member.service.MemberService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Gson gson;

    @MockBean
    MemberService memberService;

    @MockBean
    MemberMapper mapper;


    @Test
    void createMemberTest() throws Exception {

        // given
        MemberDto.Post post = new MemberDto.Post("?????????", "password", "hgd@gmail.com");
        String content = gson.toJson(post);

        MemberDto.Response responseBody = new MemberDto.Response(1l, "hgd@gmail.com", "?????????", "010-1111-2222");

        given(mapper.memberPostDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());
        given(memberService.saveMember(Mockito.any(Member.class))).willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(responseBody);

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/v1/member")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value(post.getEmail()))
                .andDo(document("post-JoinMember",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("?????????"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("????????????")
                                )
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????").optional(),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("?????????")

                                )
                        )
                ));
    }
}

   /* @Test
    void loginTest() throws Exception {

        long memberId = 1L;
        // given
        MemberDto.Login loginMember = new MemberDto.Login("hgd@gmail.com","password");
        String content = gson.toJson(loginMember);

        MemberDto.Response responseBody = new MemberDto.Response(1L, "hgd@gmail.com");

        given(mapper.memberLoginDtoToMember(Mockito.any(MemberDto.Login.class))).willReturn(new Member());
        given(memberService.login(Mockito.any(Member.class))).willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(responseBody);

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/v1/member/login")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(responseBody.getEmail()))
                .andDo(document("post-loginMember",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("?????????"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("????????????")

                                )
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????").optional(),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("?????????")

                                )
                        )
                ));
    }

}
*/