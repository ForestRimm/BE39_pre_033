package com.codestates.preproject.post.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class PostsDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {

        @Positive
        private long postId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {

        private long postId;
        private String title;
        private String content;

 public Response(String title, String content) {
   this.title = title;
   this.content = content;
      }
    }
}
