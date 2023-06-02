package org.chemax.service;

import org.chemax.dto.PostDTO;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostCreateRequest postCreateRequest);

    PostDTO getPostById(Long postId);

    void deletePostById(Long postId);

    PostDTO updatePostById(Long postId, PostUpdateRequest postUpdateRequest);

    List<PostDTO> getPostsByAuthorId(Long authorId);
}
