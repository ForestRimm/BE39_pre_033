package com.codestates.preproject.reply.repository;

import com.codestates.preproject.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}