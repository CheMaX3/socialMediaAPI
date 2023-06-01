package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.dto.PostDTO;
import org.chemax.entity.Post;
import org.chemax.repository.PostRepository;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger log = Logger.getLogger(PostServiceImpl.class.getName());
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(PostCreateRequest postCreateRequest) {
        try {
            postRepository.save(buildPostFromRequest(postCreateRequest));
        }
        catch (Exception ex) {
            log.error("Can't save object " + postCreateRequest.toString());
        }
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostDTO postDTO = new PostDTO();
        try {
            postDTO = convertPostToPostDTO(postRepository.findById(postId).orElseThrow(EntityNotFoundException::new));
        }
        catch (Exception ex) {
            log.error("Can't retrieve object from DB with id=" + postId);
        }
        return postDTO;
    }

    @Override
    public void deletePostById(Long postId) {
        try {
            postRepository.deleteById(postId);
        } catch (Exception ex) {
            log.error("Can't delete object with id=" + postId);
        }
    }

    @Override
    public void updatePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        try {
            Post postFromDB = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
            postFromDB.setHeader(Optional.ofNullable(postUpdateRequest.getHeader()).orElse(postFromDB.getHeader()));
            postFromDB.setMessage(Optional.ofNullable(postUpdateRequest.getMessage()).orElse(postFromDB.getMessage()));
            postFromDB.setUpdatedDateTime(ZonedDateTime.now());
            postRepository.save(postFromDB);
        } catch (Exception ex) {
            log.error("Can't save object with id=" + postId);
        }
    }

    private Post buildPostFromRequest(PostCreateRequest postCreateRequest) throws IOException {
        Post builtPost = new Post();
        builtPost.setHeader(postCreateRequest.getHeader());
        builtPost.setMessage(postCreateRequest.getMessage());
        builtPost.setCreationDateTime(ZonedDateTime.now());
        builtPost.setAuthorId(postCreateRequest.getAuthorId());
        return builtPost;
    }

    private PostDTO convertPostToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(post.getPostId());
        postDTO.setHeader(post.getHeader());
        postDTO.setMessage(post.getMessage());
        postDTO.setAuthorId(post.getAuthorId());
        return postDTO;
    }
}
