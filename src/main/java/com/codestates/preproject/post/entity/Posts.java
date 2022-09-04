package com.codestates.preproject.post.entity;

import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.reply.entity.PostsReply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Posts extends Auditable {
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

    @OneToMany(mappedBy = "posts", cascade = CascadeType.PERSIST)
    private List<PostsReply> postsReplies = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void addPostsReply(PostsReply postsReply) {
        this.postsReplies.add(postsReply);
        if (postsReply.getPosts() != this) {
            postsReply.addPosts(this);
        }
    }

    public Posts(long postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }
}
