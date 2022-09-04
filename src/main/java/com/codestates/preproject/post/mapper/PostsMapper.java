package com.codestates.preproject.post.mapper;

import com.codestates.preproject.post.dto.PostsDto;
import com.codestates.preproject.post.dto.PostsPatchDto;
import com.codestates.preproject.post.dto.PostsPostDto;
import com.codestates.preproject.post.entity.Posts;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostsMapper {

    Posts postsPostDtoToPosts(PostsPostDto postsPostDto);
    Posts postsPatchDtoToPosts(PostsPatchDto postsPatchDto);
    PostsDto.Response postsToPostsDtoResponse(Posts posts);
    List<PostsDto.Response> postsToPostsResponses(List<Posts> posts);
}
