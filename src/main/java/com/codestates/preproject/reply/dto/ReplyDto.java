package com.codestates.preproject.reply.dto;

import com.codestates.preproject.member.dto.MemberDto;
import lombok.*;

import javax.validation.constraints.NotBlank;


public class ReplyDto {


    @Getter
    public static class Post {

        @NotBlank(message = "공백이 될 수 없습니다.")
        private String content;
    }

    @Getter
    public static class Patch {

        @NotBlank(message = "공백이 될 수 없습니다.")
        private String content;
    }

    //public void setReplyId(long replyId) {
    //    this.replyId = replyId;
   // }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {


        private long replyId;
        private String content;

    }
}
