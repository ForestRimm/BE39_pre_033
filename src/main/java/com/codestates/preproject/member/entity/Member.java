package com.codestates.preproject.member.entity;


import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.post.entity.Posts;
import com.codestates.preproject.reply.entity.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.Email;
//import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;


    //unique 추가
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @Column(name = "email", updatable = false, unique = true)
    private String email;
    @Column(name = "phone", updatable = false, unique = true)
    private String phone;
    private String roles; // User, MANAGER, ADMIN

    //private String provider;
    //private String providerId;

    @Builder // 추가
    public Member(String username, String password, String email, String roles, String provider, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone =phone;
        this.roles = roles;
       //this.provider = provider;
        //this.providerId = providerId;
    }


    @OneToMany(mappedBy = "member")
    private List<Posts> posts;

    @OneToMany(mappedBy = "posts")
    private List<Reply> reply;



    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}

