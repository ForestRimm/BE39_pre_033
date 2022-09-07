package com.codestates.preproject.reply.controller;

import com.codestates.preproject.member.entity.Member;
import com.codestates.preproject.dto.MultiResponseDto;
import com.codestates.preproject.dto.SingleResponseDto;
import com.codestates.preproject.post.entity.Posts;
import com.codestates.preproject.reply.dto.ReplyDto;
//import com.codestates.preproject.reply.dto.ReplyPatchDto;
//import com.codestates.preproject.reply.dto.ReplyPostDto;
import com.codestates.preproject.reply.entity.Reply;
import com.codestates.preproject.reply.mapper.ReplyMapper;
import com.codestates.preproject.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/posts/{postId}/reply")
@RestController
@Validated
public class ReplyController {

    private final ReplyService replyService;
    private final ReplyMapper mapper;

    @PostMapping
    public ResponseEntity createReply(@PathVariable("postId") long postId,
   //                                   @AuthenticationPrincipal PrincipalDetails principal,
                                      @RequestBody ReplyDto.Post replyPost) {

        Reply reply = mapper.ReplyPostDtoToReply(replyPost);
        replyService.createReply(postId, reply.getMember(), reply);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.ReplyToReplyDtoResponse(reply)), HttpStatus.CREATED);
    }

    @PatchMapping("/{replyId}")
    public ResponseEntity updateEntity(@PathVariable("replyId") long replyId,
    //                                   @AuthenticationPrincipal PrincipalDetails principal,
                                       @RequestBody ReplyDto.Patch replyPatch) {

        Reply reply = mapper.ReplyPatchDtoToReply(replyPatch);
        reply.setReplyId(replyId);
        Reply response = replyService.updateReply(reply.getMember(), reply);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.ReplyToReplyDtoResponse(response)), HttpStatus.OK);
    }

    @DeleteMapping("/{replyId}")
    public ResponseEntity deleteEntity(@PathVariable("replyId") long replyId
     //                                  @AuthenticationPrincipal PrincipalDetails principal
                                                                         ) {

       // replyService.deleteReply(reply.getMember, replyId);

        return new ResponseEntity<>("댓글이 삭제되었습니다.", HttpStatus.NO_CONTENT);
    }


}