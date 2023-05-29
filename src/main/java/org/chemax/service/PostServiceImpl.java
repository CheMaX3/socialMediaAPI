package org.chemax.service;

import org.chemax.dto.PostDTO;
import org.chemax.entity.Post;
import org.chemax.repository.PostRepository;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(PostCreateRequest postCreateRequest) {
        try {
            Post post = postRepository.save(buildPostFromRequest(postCreateRequest));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostDTO postDTO = new PostDTO();
        try {
            postDTO = convertPostToPostDTO(postRepository.findById(postId).orElseThrow(EntityNotFoundException::new));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return postDTO;
    }

    @Override
    public void deletePostById(Long postId) {
        try {
            postRepository.delete(postRepository.findById(postId).orElseThrow(EntityNotFoundException::new));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updatePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        try {
            Post postFromDB = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
            postFromDB.setHeader(Optional.ofNullable(postUpdateRequest.getHeader()).orElse(postFromDB.getHeader()));
            postFromDB.setMessage(Optional.ofNullable(postUpdateRequest.getMessage()).orElse(postFromDB.getMessage()));
            postFromDB.setImages(Optional.ofNullable(postUpdateRequest.getContent()).orElse(postFromDB.getImages()));
            postFromDB.setUpdatedDateTime(ZonedDateTime.now());
            postRepository.save(postFromDB);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Post buildPostFromRequest(PostCreateRequest postCreateRequest) {
        Post builtPost = new Post();
        builtPost.setHeader(postCreateRequest.getHeader());
        builtPost.setMessage(postCreateRequest.getMessage());
        builtPost.setImages(postCreateRequest.getContent());
        builtPost.setCreationDateTime(ZonedDateTime.now());
        builtPost.setAuthor(postCreateRequest.getAuthor());
        return builtPost;
    }

    private PostDTO convertPostToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(post.getPostId());
        postDTO.setHeader(post.getHeader());
        postDTO.setMessage(post.getMessage());
        postDTO.setImages(Optional.ofNullable(post.getImages()).orElse(new ArrayList<>()));
        postDTO.setCreationDateTime(post.getCreationDateTime());
        postDTO.setUpdatedDateTime(post.getUpdatedDateTime());//TODO:подумать что возвращать
        postDTO.setAuthor(post.getAuthor());
        return postDTO;
    }
}
