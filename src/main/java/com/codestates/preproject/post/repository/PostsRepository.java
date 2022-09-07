package com.codestates.preproject.post.repository;

import com.codestates.preproject.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> { //<어떤 엔티티를 넣을거냐, 엔티티의 primary key 로(@Id) 지정한 컬럼의 타입(postId의 타입)>
}
