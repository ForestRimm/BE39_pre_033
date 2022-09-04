package com.codestates.preproject.post.service;

import com.codestates.preproject.post.entity.Posts;
import com.codestates.preproject.post.repository.PostsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PostsService {
    private final PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }


    /**
     * 글 등록
     */
    public Posts createPosts(Posts postsPost) {

        return postsRepository.save(postsPost);


    }


    /**
     * 글 수정
     */
    public Posts updatePosts(Posts patchPost) {

        Posts findPosts = existPosts(patchPost.getPostId());

        Optional.ofNullable(patchPost.getTitle())
                .ifPresent(findPosts::setTitle);
        Optional.ofNullable(patchPost.getContent())
                .ifPresent(findPosts::setContent);

        return postsRepository.save(findPosts);
    }

    /**
     * 특정 글 조회
     */
    public Posts findPosts(long postId) {

        return existPosts(postId);
    }

    /**
     * 전체 글 조회
     */
    public Page<Posts> findAllPosts(int page, int size) {
        return postsRepository.findAll(PageRequest.of(page,size, Sort.by("postId").descending()));
    }


    /**
     * 특정 글 삭제
     */
    public void deletePosts(long postId) {

        Posts findPosts = existPosts(postId);

        postsRepository.delete(findPosts);
    }


    /**
     * PostId 존재하지 않을 경우 Exception
     */
    private Posts existPosts(long postId) {

        Optional<Posts> existPosts = postsRepository.findById(postId);

        return existPosts.orElseThrow(() ->
                new RuntimeException("PostId not exist"));
    }
}

