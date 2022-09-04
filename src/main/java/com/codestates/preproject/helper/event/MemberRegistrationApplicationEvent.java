package com.codestates.preproject.helper.event;

import com.codestates.preproject.member.entity.Member;
import org.springframework.context.ApplicationEvent;

public class MemberRegistrationApplicationEvent extends ApplicationEvent {
    private Member member;
    public MemberRegistrationApplicationEvent(Object source, Member member) {
        super(source);
        this.member = member;
    }
}
