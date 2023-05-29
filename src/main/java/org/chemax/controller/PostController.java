package org.chemax.controller;

import org.chemax.dto.PostDTO;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PostController {
    @PostMapping(value = "/post_create")
    ResponseEntity<Void> createPost(@RequestBody PostCreateRequest postCreateRequest);

    @GetMapping(value = "/post_get")
    ResponseEntity<PostDTO> getPostById(@RequestParam Long postId);

    @DeleteMapping(value = "/post_delete")
    ResponseEntity<Void> deletePostById(@RequestParam Long postId);

    @PutMapping(value = "/post_update")
    ResponseEntity<Void> updatePostById(@RequestParam Long postId,
                                        @RequestBody PostUpdateRequest postUpdateRequest);
}
