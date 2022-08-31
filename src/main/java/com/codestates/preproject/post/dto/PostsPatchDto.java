package com.codestates.preproject.post.dto;

import lombok.Getter;

@Getter
public class PostsPatchDto {
        private long postsId;

        private String title;

        private String content;

        public void setPostId(long postsId) {
                this.postsId = postsId;
        }


}
