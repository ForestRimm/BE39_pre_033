package com.codestates.preproject.reply.entity;

import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.post.entity.Posts;
import com.codestates.preproject.post.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class PostsReply extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postsReplyId;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Reply reply;

    public void addPosts(Posts posts) {
        this.posts = posts;
        if (!this.posts.getPostsReplies().contains(this)) {
            this.posts.getPostsReplies().add(this);
        }
    }

    public void addReply(Reply reply) {
        this.reply = reply;
        if (!this.reply.getPostsReplies().contains(this)) {
            this.reply.addPostsReply(this);
        }
    }
}
