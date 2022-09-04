package com.codestates.preproject.post.dto;

import lombok.Getter;

@Getter
public class PostsPatchDto {
        private long postId;

        private String title;

        private String content;

        public void setPostId(long postId) {
                this.postId = postId;
        }


}
