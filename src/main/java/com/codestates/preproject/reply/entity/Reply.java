package com.codestates.preproject.reply.entity;

import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.post.entity.Posts;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long replyId;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = " postsId")
    private Posts posts;


    //댓글 조회시 member 정보도 같이 띄워야 하므로 FetchType.EAGER 설정
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "reply")
    private List<PostsReply> postsReplies = new ArrayList<>();

    public void addPostsReply(PostsReply postsReply) {
        this.postsReplies.add(postsReply);
        if (postsReply.getReply() != this) {
            postsReply.addReply(this);

        }
    }
}