package com.codestates.preproject.post.repository;

import com.codestates.preproject.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
