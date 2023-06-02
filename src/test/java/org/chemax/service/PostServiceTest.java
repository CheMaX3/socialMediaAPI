package org.chemax.service;

import org.chemax.entity.Post;
import org.chemax.repository.PostRepository;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    void create_post() {
        var postCreateRequest = buildPostCreateRequest();
        var post = postService.createPost(postCreateRequest);

        assertTrue(Objects.nonNull(post.getPostId()));
    }

    @Test
    @Transactional
    void get_post() {
        var post = createPost();
        var postById = postService.getPostById(post.getPostId());

        assertNotNull(postById);
        assertEquals(post.getAuthorId(), postById.getAuthorId());
        assertEquals(post.getHeader(), postById.getHeader());
        assertEquals(post.getMessage(), postById.getMessage());
    }

    @Test
    @Transactional
    void update_post() {
        var post = createPost();
        var postUpdateRequest = buildPostUpdateRequest();

        var postDTO = postService.updatePostById(post.getPostId(), postUpdateRequest);
        assertNotNull(postDTO);
        assertEquals(postDTO.getPostId(), post.getPostId());
        assertEquals(postDTO.getHeader(), postUpdateRequest.getHeader());
        assertEquals(postDTO.getMessage(), postUpdateRequest.getMessage());
    }

    @Test
    @Transactional
    void delete_post() {
        var post = createPost();

        postService.deletePostById(post.getPostId());
    }

    private Post createPost() {
        Post post = new Post();
        post.setHeader("header");
        post.setMessage("message");
        post.setAuthorId(1L);
        post.setCreationDateTime(ZonedDateTime.now());

        return postRepository.save(post);
    }

    private PostUpdateRequest buildPostUpdateRequest() {
        PostUpdateRequest postUpdateRequest = new PostUpdateRequest();
        postUpdateRequest.setHeader("new_header");
        postUpdateRequest.setMessage("new_message");

        return postUpdateRequest;
    }

    private PostCreateRequest buildPostCreateRequest() {
        PostCreateRequest postCreateRequest = new PostCreateRequest();
        postCreateRequest.setHeader("header");
        postCreateRequest.setMessage("message");
        postCreateRequest.setAuthorId(1L);

        return postCreateRequest;
    }
}
