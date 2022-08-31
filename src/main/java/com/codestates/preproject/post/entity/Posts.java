package com.codestates.preproject.post.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Member;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @JoinColumn(name = "memberId")
    @ManyToOne
    private Member member;

    public Posts(long postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }
}
