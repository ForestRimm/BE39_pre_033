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
//import javax.validation.constraints.Email;
import java.util.ArrayList;
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
    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    //@Email
    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;

   // @Enumerated(value = EnumType.STRING)
    //@Column(length = 20, nullable = false)
    //private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

   // private String provider;
   // private String providerId;


    @OneToMany(mappedBy = "member")
    private List<Posts> posts = new ArrayList<>();

    @OneToMany(mappedBy = "posts")
    private List<Reply> reply = new ArrayList<>();


    //@Builder // 추가
    public Member(String name, String password, String email, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        //   this.roles = roles;
        this.phone = phone;
        //   this.provider = provider;
        //   this.providerId = providerId;
    }


//    public void setPosts(Posts posts) {
//        posts.add(posts);
//        if (posts.getMember() != this) {
//            posts.setMember(this);
//        }

      //  public enum MemberStatus {
      //      MEMBER_ACTIVE("활동중"),
       //     MEMBER_SLEEP("휴면 상태"),
       //     MEMBER_QUIT("탈퇴 상태");

     //       @Getter
      //      private String status;

     //       MemberStatus(String status) {
     //           this.status = status;
     //       }
  //      }
    }



    /* public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    } */


