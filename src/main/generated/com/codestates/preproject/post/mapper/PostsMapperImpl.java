package com.codestates.preproject.post.mapper;

import com.codestates.preproject.post.dto.PostsDto;
import com.codestates.preproject.post.dto.PostsPatchDto;
import com.codestates.preproject.post.dto.PostsPostDto;
import com.codestates.preproject.post.entity.Posts;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-04T15:17:23+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class PostsMapperImpl implements PostsMapper {

    @Override
    public Posts postsPostDtoToPosts(PostsPostDto postsPostDto) {
        if ( postsPostDto == null ) {
            return null;
        }

        Posts posts = new Posts();

        return posts;
    }

    @Override
    public Posts postsPatchDtoToPosts(PostsPatchDto postsPatchDto) {
        if ( postsPatchDto == null ) {
            return null;
        }

        Posts posts = new Posts();

        posts.setPostId( postsPatchDto.getPostId() );
        posts.setTitle( postsPatchDto.getTitle() );
        posts.setContent( postsPatchDto.getContent() );

        return posts;
    }

    @Override
    public PostsDto.Response postsToPostsDtoResponse(Posts posts) {
        if ( posts == null ) {
            return null;
        }

        PostsDto.Response.ResponseBuilder response = PostsDto.Response.builder();

        response.postId( posts.getPostId() );
        response.title( posts.getTitle() );
        response.content( posts.getContent() );

        return response.build();
    }

    @Override
    public List<PostsDto.Response> postsToPostsResponses(List<Posts> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostsDto.Response> list = new ArrayList<PostsDto.Response>( posts.size() );
        for ( Posts posts1 : posts ) {
            list.add( postsToPostsDtoResponse( posts1 ) );
        }

        return list;
    }
}
