package org.chemax.service;

import org.chemax.dto.PostDTO;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;

public interface PostService {
    void createPost(PostCreateRequest postCreateRequest);

    PostDTO getPostById(Long postId);

    void deletePostById(Long postId);

    void updatePostById(Long postId, PostUpdateRequest postUpdateRequest);
}
