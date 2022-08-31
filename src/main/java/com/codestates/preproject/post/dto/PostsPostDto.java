package com.codestates.preproject.post.dto;

import lombok.*;

public class PostsPostDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        private String title;

        private String content;


        }
    }
