package org.chemax.controller;

import org.chemax.dto.PostDTO;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO:Swagger annotations

@RequestMapping(value = "/post")
public interface PostController {

    @PostMapping(value = "/create")
    ResponseEntity<PostDTO> createPost(@RequestBody PostCreateRequest postCreateRequest);

    @GetMapping(value = "/get")
    ResponseEntity<PostDTO> getPostById(@RequestParam Long postId);

    @DeleteMapping(value = "/delete")
    ResponseEntity<Void> deletePostById(@RequestParam Long postId);

    @PutMapping(value = "/update")
    ResponseEntity<PostDTO> updatePostById(@RequestParam Long postId,
                                           @RequestBody PostUpdateRequest postUpdateRequest);

    @GetMapping(value = "get_posts_by_author")
    ResponseEntity<List<PostDTO>> getPostsByAuthorId(@RequestParam Long authorId);

    @GetMapping(value = "get_feed")
    ResponseEntity<List<PostDTO>> getFeedByUserId(@RequestParam Long userId);
}
