package org.chemax.controller;

import org.chemax.dto.PostDTO;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/post")
public interface PostController {
    @PostMapping(value = "/create")
    ResponseEntity<Void> createPost(@RequestBody PostCreateRequest postCreateRequest);

    @GetMapping(value = "/get")
    ResponseEntity<PostDTO> getPostById(@RequestParam Long postId);

    @DeleteMapping(value = "/delete")
    ResponseEntity<Void> deletePostById(@RequestParam Long postId);

    @PutMapping(value = "/update")
    ResponseEntity<Void> updatePostById(@RequestParam Long postId,
                                        @RequestBody PostUpdateRequest postUpdateRequest);
}
