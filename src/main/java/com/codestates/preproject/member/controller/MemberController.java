package com.codestates.preproject.member.controller;

import com.codestates.preproject.member.dto.MemberDto;
import com.codestates.preproject.member.entity.Member;
import com.codestates.preproject.dto.SingleResponseDto;
import com.codestates.preproject.member.mapper.MemberMapper;
import com.codestates.preproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import java.lang.reflect.Member;


@RestController
//@RequiredArgsConstructor
@RequestMapping("/v1/member")
@Validated
public class MemberController {
    private final MemberService memberService;

    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    // 추가
    @PostMapping
    public ResponseEntity join(@Validated @RequestBody MemberDto.Post postMember) {

        Member member = (Member) mapper.memberPostDtoToMember(postMember);
        memberService.saveMember(member);
        return new ResponseEntity<>("회원 가입 완료", HttpStatus.OK);
    }
/*
    @GetMapping("/user")
    public ResponseEntity user() {
        return new ResponseEntity<>("user 권한을 가지고 있습니다.", HttpStatus.OK);
    }

    @GetMapping("/manager")
    public ResponseEntity manager() {
        return new ResponseEntity<>("manager 권한을 가지고 있습니다.", HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity admin() {
        return new ResponseEntity<>("admin 권한을 가지고 있습니다. ", HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity login() {

        return new ResponseEntity<>("토큰이 존재하지 않습니다. ", HttpStatus.OK);
    }
*/

    @GetMapping("/{member-id}")
    public ResponseEntity findMember(@PathVariable("member-id") long memberId) {

        Member findMember = (Member) memberService.findMember(memberId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(findMember))
                , HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public void deleteMember(@PathVariable("member-id") long memberId) {

        memberService.deleteMember(memberId);
    }
}
