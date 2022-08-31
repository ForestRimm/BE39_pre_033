package com.codestates.preproject.post.dto;

public class PostsResponseDto {
    private long postsId;
    private String title;
    private String content;

    public void Response(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
