package com.codestates.preproject.reply.mapper;

import com.codestates.preproject.reply.dto.ReplyDto;
//import com.codestates.preproject.reply.dto.ReplyPatchDto;
//import com.codestates.preproject.reply.dto.PostsPostDto;
import com.codestates.preproject.reply.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReplyMapper {

    Reply ReplyPostDtoToReply(ReplyDto.Post replyDto);
    Reply ReplyPatchDtoToReply(ReplyDto.Patch replyDto);
    ReplyDto.Response ReplyToReplyDtoResponse(Reply reply);

}