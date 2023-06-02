package org.chemax.controller;

import org.chemax.dto.PostDTO;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;
import org.chemax.service.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostControllerImpl implements PostController {

    private final PostServiceImpl postService;

    public PostControllerImpl(PostServiceImpl postService) {
        this.postService = postService;
    }

    @Override
    public ResponseEntity<PostDTO> createPost(PostCreateRequest postCreateRequest) {
        final PostDTO postDTO = postService.createPost(postCreateRequest);
        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PostDTO> getPostById(Long postId) {
        final PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletePostById(Long postId) {
        postService.deletePostById(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDTO> updatePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        final PostDTO postDTO = postService.updatePostById(postId, postUpdateRequest);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PostDTO>> getPostsByAuthorId(Long authorId) {
        final List<PostDTO> postList = postService.getPostsByAuthorId(authorId);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PostDTO>> getFeedByUserId(Long userId) {
        final List<PostDTO> feed = postService.getFeedByUserId(userId);
        return new ResponseEntity<>(feed, HttpStatus.OK);
    }
}
