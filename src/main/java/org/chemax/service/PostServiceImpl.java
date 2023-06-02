package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.dto.PostDTO;
import org.chemax.entity.Post;
import org.chemax.entity.Subscriber;
import org.chemax.repository.PostRepository;
import org.chemax.repository.SubscriberRepository;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger log = Logger.getLogger(PostServiceImpl.class.getName());
    private final PostRepository postRepository;
    private final SubscriberRepository subscriberRepository;

    public PostServiceImpl(PostRepository postRepository, SubscriberRepository subscriberRepository) {
        this.postRepository = postRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public PostDTO createPost(PostCreateRequest postCreateRequest) {
        PostDTO postDTO = new PostDTO();
        try {
            postDTO = convertPostToPostDTO(postRepository.save(buildPostFromRequest(postCreateRequest)));
        }
        catch (Exception ex) {
            log.error("Can't save object " + postCreateRequest.toString());
        }
        return postDTO;
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
    public PostDTO updatePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        PostDTO postDTO = new PostDTO();
        try {
            Post postFromDB = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
            postFromDB.setHeader(Optional.ofNullable(postUpdateRequest.getHeader()).orElse(postFromDB.getHeader()));
            postFromDB.setMessage(Optional.ofNullable(postUpdateRequest.getMessage()).orElse(postFromDB.getMessage()));
            postFromDB.setUpdatedDateTime(ZonedDateTime.now());
            postDTO = convertPostToPostDTO(postRepository.save(postFromDB));
        } catch (Exception ex) {
            log.error("Can't save object with id=" + postId);
        }
        return postDTO;
    }

    @Override
    public List<PostDTO> getPostsByAuthorId(Long authorId) {
        List<PostDTO> postList = new ArrayList<>();
        try {
            postList = postRepository.findByAuthorId(authorId).stream().map(this::convertPostToPostDTO)
                    .collect(Collectors.toList());
        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB with authorId=" + authorId);
        }
        return postList;
    }

    @Override
    public List<PostDTO> getFeedByUserId(Long userId) {
        Pageable firstPageWithFiveElements = PageRequest
                .of(0, 5, Sort.by("creationDateTime").descending());
        List<Subscriber> subcriberList = subscriberRepository.findByRequestedId(userId);

        List<List<Post>> collect = subcriberList.stream()
                .map(subscriber -> subscriber.getRequestedId())
                .map(subId -> postRepository.findByAuthorId(subId))
                .collect(Collectors.toList());


        List<List<Subscriber>> lists = postRepository.findAll().stream()
                .map(post -> subcriberList.stream()
                        .filter(e -> e.getRequestedId().equals(post.getAuthorId())).toList())
                .toList();
        Page<Post> feed = postRepository.findAll(firstPageWithFiveElements);
        return feed.stream().map(this::convertPostToPostDTO).collect(Collectors.toList());
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
