package com.codestates.preproject.member.mapper;

import com.codestates.preproject.member.dto.MemberDto;
import com.codestates.preproject.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;



@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    Member memberPostDtoToMember(MemberDto.Post requestBody);
    MemberDto.Response memberToMemberResponseDto(Member member);

}