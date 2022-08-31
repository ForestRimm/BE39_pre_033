package com.codestates.preproject;

import com.codestates.preproject.post.controller.PostsController;
import com.codestates.preproject.post.dto.PostsDto;
import com.codestates.preproject.post.dto.PostsPatchDto;
import com.codestates.preproject.post.dto.PostsPostDto;
import com.codestates.preproject.post.entity.Posts;
import com.codestates.preproject.post.mapper.PostsMapper;
import com.codestates.preproject.post.service.PostsService;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;


@WebMvcTest(PostsController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class PostsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostsService postsService;

    @MockBean
    private PostsMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void postPostsTest() throws Exception {
        // given
        PostsDto.Post post = new PostsDto.Post("title1","content1");
        String content = gson.toJson(post);

        PostsDto.Response response =
                new PostsDto.Response(1L,
                        "title1",
                        "content1");

        // willReturn()이 최소한 null은 아니어야 한다.
        given(mapper.postsPostDtoToPosts(Mockito.any(PostsPostDto.class))).willReturn(new Posts());

        given(postsService.createPosts(Mockito.any(Posts.class))).willReturn(new Posts());

        given(mapper.postsToPostsDtoResponse(Mockito.any(Posts.class))).willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/v1/posts")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.postId").value(1L))
                .andExpect(jsonPath("$.data.title").value(post.getTitle()))
                .andExpect(jsonPath("$.data.content").value(post.getContent()))
                .andDo(document("post-post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용")

                                )
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터").optional(),
                                        fieldWithPath("data.postId").type(JsonFieldType.NUMBER).description("포스트 번호"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용")
                                )
                        )
                ));
    }

    @Test
    public void patchPostsTest() throws Exception {
        // given
        long postId = 1L;
        PostsDto.Patch patch = new PostsDto.Patch(postId, "title1", "content1");
        String content = gson.toJson(patch);

        PostsDto.Response responseDto =
                new PostsDto.Response(
                        1L,
                        "title1",
                        "content1");

        // willReturn()이 최소한 null은 아니어야 한다.
        given(mapper.postsPatchDtoToPosts(Mockito.any(PostsPatchDto.class))).willReturn(new Posts());

        given(postsService.updatePosts(Mockito.any(Posts.class))).willReturn(new Posts());

        given(mapper.postsToPostsDtoResponse(Mockito.any(Posts.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders
                                .patch("/v1/posts/{post-id}", postId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.postId").value(patch.getPostId()))
                .andExpect(jsonPath("$.data.title").value(patch.getTitle()))
                .andExpect(jsonPath("$.data.content").value(patch.getContent()))
                .andDo(document("patch-post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("post-id").description("포스트 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("postId").type(JsonFieldType.NUMBER).description("포스트 식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용")
                                )
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터").optional(),
                                        fieldWithPath("data.postId").type(JsonFieldType.NUMBER).description("포스트 식별자"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용")
                                )
                        )
                ));
    }

    @Test
    public void getPostTest() throws Exception {
        // given
        long postsId = 1L;
        PostsDto.Response response = new PostsDto.Response(1L,"title1", "contents");

        given(postsService.findPosts(Mockito.anyLong())).willReturn(new Posts());
        given(mapper.postsToPostsDtoResponse(Mockito.any(Posts.class))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/v1/posts/{post-id}",postsId)
                        .accept(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.postId").value(1L))
                .andExpect(jsonPath("$.data.title").value(response.getTitle()))
                .andExpect(jsonPath("$.data.content").value(response.getContent()))
                .andDo(
                        document("get-post",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        Arrays.asList(parameterWithName("post-id").description("포스트 식별자"))
                                ),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터").optional(),
                                                fieldWithPath("data.postsId").type(JsonFieldType.NUMBER).description("포스트 식별자 ID"),
                                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용")
                                        )
                                )
                        ));
    }

    @Test
    public void getPostsTest() throws Exception {
        // given
        String page = "1";
        String size = "10";

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        Posts post1 = new Posts(1L,"title1", "content1");
        Posts post2 = new Posts(2L,"title2", "content2");


        Page<Posts> posts = new PageImpl<>(List.of(post1, post2),
                PageRequest.of(0, 10, Sort.by("postId").descending()), 2);

        List<PostsDto.Response> responses = getMultiResponseBody();


        given(postsService.findAllPosts(Mockito.anyInt(), Mockito.anyInt())).willReturn(posts);
        given(mapper.postsToPostsDtoResponse(Mockito.any(Posts.class))).willReturn((PostsDto.Response) responses);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/v1/posts")
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON));

        // then
        MvcResult result = actions
                                .andExpect(status().isOk())
                                .andDo(
                                document(
                                        "get-posts",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        requestParameters(
                                                getDefaultRequestParameterDescriptors()
                                        ),
                                        responseFields(
                                                Arrays.asList(
                                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
                                                        fieldWithPath("data[].postsId").type(JsonFieldType.NUMBER).description("포스트 식별자"),
                                                        fieldWithPath("data[].title").type(JsonFieldType.STRING).description("제목"),
                                                        fieldWithPath("data[].content").type(JsonFieldType.STRING).description("내용"),
                                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보").optional(),
                                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 번호").optional(),
                                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 사이즈").optional(),
                                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 건 수").optional(),
                                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수").optional()
                                                )
                                        )
                                )
                        )
                        .andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
        assertThat(list.size(), is(2));


    }



    @Test
    void deletePostTest() throws Exception {
        // given
        long postId = 1L;

        doNothing().when(postsService).deletePosts(postId);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders
                     .delete("/v1/posts/{post-id}", postId));
        // then
        actions.andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-post",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        Arrays.asList(parameterWithName("posts-id").description("포스트 식별자"))
                                )
                        )
                );
    }

    public static List<PostsDto.Response> getMultiResponseBody() {
        return List.of(
                new PostsDto.Response(1L,"title1", "contents1"),
                new PostsDto.Response(2L,"title2", "contents2")
        );
    }

    public List<ParameterDescriptor> getDefaultRequestParameterDescriptors() {
        return List.of(
                parameterWithName("page").description("Page 번호"),
                parameterWithName("size").description("Page Size")
        );
    }
}
