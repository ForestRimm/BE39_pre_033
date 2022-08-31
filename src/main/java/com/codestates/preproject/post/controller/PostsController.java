package com.codestates.preproject.post.controller;

import com.codestates.preproject.dto.MultiResponseDto;
import com.codestates.preproject.post.dto.PostsPatchDto;
import com.codestates.preproject.post.dto.PostsPostDto;
import com.codestates.preproject.dto.SingleResponseDto;
import com.codestates.preproject.post.entity.Posts;
import com.codestates.preproject.post.mapper.PostsMapper;
import com.codestates.preproject.post.service.PostsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/posts")
@Validated
public class PostsController {

    private final PostsService postsService;
    private final PostsMapper mapper;

    public PostsController(PostsService postsService, PostsMapper mapper) {
        this.postsService = postsService;
        this.mapper = mapper;
    }

    /**
     * 글 관리 ( 글 작성 / 글 수정 /특정 글 조회 / 전체 글 목록 / 글 삭제 )
     */

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostsPostDto postsPostDto) {

        Posts post = postsService.createPosts(mapper.postsPostDtoToPosts(postsPostDto));


        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.postsToPostsDtoResponse(post)),
                HttpStatus.CREATED);
    }


    @PatchMapping("/{post-id}")
    public ResponseEntity patchPost(@PathVariable("post-id") long postId,
                                    @RequestBody PostsPatchDto postsPatchDto) {

        postsPatchDto.setPostId(postId);
        Posts post = postsService.updatePosts(mapper.postsPatchDtoToPosts(postsPatchDto));

       // post.setPostId(post.getPostId());
        //Posts response = postsService.updatePosts(mapper.postsPatchDtoToPosts(post));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.postsToPostsDtoResponse(post)),
                HttpStatus.OK);
    }


    @GetMapping("/filter")
    public ResponseEntity searchPosts(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "size", defaultValue = "10") int size,
                                      @RequestParam(value = "type", defaultValue = "1") long typeCriteria,
                                      @RequestParam(value = "post-id", defaultValue = "1") long postId) {


        Posts post = postsService.findPost(postId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.postsToPostsDtoResponse(post)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getPosts( @RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
          Page<Posts> pagePosts = postsService.findAllPosts(page - 1, size);
          List<Posts> posts = pagePosts.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.postsToPostsResponses(posts),
                        pagePosts),
                HttpStatus.OK);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePosts(@PathVariable("post-id") long postId) {
        postsService.deletePosts(postId);

        return new ResponseEntity<>("삭제 완료", HttpStatus.NO_CONTENT);
    }
}