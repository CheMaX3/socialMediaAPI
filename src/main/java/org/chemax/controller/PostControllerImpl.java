package org.chemax.controller;

import org.chemax.dto.PostDTO;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;
import org.chemax.service.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostControllerImpl implements PostController {

    private final PostServiceImpl postService;

    public PostControllerImpl(PostServiceImpl postService) {
        this.postService = postService;
    }

    @Override
    public ResponseEntity<Void> createPost(PostCreateRequest postCreateRequest) {
        postService.createPost(postCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
    public ResponseEntity<Void> updatePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        postService.updatePostById(postId, postUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
