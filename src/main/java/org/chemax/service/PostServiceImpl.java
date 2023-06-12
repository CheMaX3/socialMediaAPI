package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.dto.PostDTO;
import org.chemax.entity.Post;
import org.chemax.entity.Subscriber;
import org.chemax.exception.FieldCantBeEmptyException;
import org.chemax.repository.PostRepository;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger log = Logger.getLogger(PostServiceImpl.class.getName());

    private final PostRepository postRepository;

    private final SubscriberService subscriberService;

    public PostServiceImpl(PostRepository postRepository, SubscriberService subscriberService) {
        this.postRepository = postRepository;
        this.subscriberService = subscriberService;
    }

    @Override
    public PostDTO createPost(PostCreateRequest postCreateRequest) {
        try {
            Post post = buildPostFromRequest(postCreateRequest);
            postRepository.save(post);
            return convertPostToPostDTO((post));
        }
        catch (Exception ex) {
            log.error("Can't save object " + postCreateRequest.toString());
            throw new DataSourceLookupFailureException("Can't save object " + postCreateRequest);
        }
    }

    @Override
    public PostDTO getPostById(Long postId) {
        try {
            Post postFromDB = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
            return convertPostToPostDTO(postFromDB);
        }
        catch (Exception ex) {
            log.error("Can't retrieve object from DB with id=" + postId);
            throw new DataSourceLookupFailureException("Can't retrieve object from DB");
        }
    }

    @Override
    public void deletePostById(Long postId) {
        try {
            postRepository.deleteById(postId);
        } catch (Exception ex) {
            log.error("Post with postId=" + postId + " not exists");
            throw new EntityNotFoundException("Post with postId=" + postId + " not exists");
        }
    }

    @Override
    public PostDTO updatePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        try {
            Post postFromDB = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
            postFromDB.setHeader(Optional.ofNullable(postUpdateRequest.getHeader()).orElse(postFromDB.getHeader()));
            postFromDB.setMessage(Optional.ofNullable(postUpdateRequest.getMessage()).orElse(postFromDB.getMessage()));
            postFromDB.setUpdatedDateTime(ZonedDateTime.now());
            postRepository.save(postFromDB);

            return convertPostToPostDTO(postFromDB);

        }
        catch (Exception ex) {
            log.error("Can't find post with postId=" + postId);
            throw new EntityNotFoundException("Can't find post with postId=" + postId);
        }
    }

    @Override
    public List<PostDTO> getPostsByAuthorId(Long authorId) {
        try {
            return postRepository.findByAuthorId(authorId).stream().map(this::convertPostToPostDTO)
                    .collect(Collectors.toList());
        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB with authorId=" + authorId);
            throw new EntityNotFoundException("Can't retrieve objects from DB with authorId=" + authorId);
        }
    }

    @Override
    public List<PostDTO> getFeedByUserId(Long userId, Integer pageNumber, Integer postCount) {
        Pageable pageable = PageRequest
                .of(pageNumber, postCount, Sort.by("creationDateTime").descending());
        try {
            List<Subscriber> subscriberList = subscriberService.getSubscriberListByRequesterId(userId);
            Page<Post> postList = postRepository.findAll(pageable);
            List<Post> feed = new ArrayList<>();
            for (Post post : postList) {
                for (Subscriber subscriber : subscriberList) {
                    if (subscriber.getRequestedId().equals(post.getAuthorId())) {
                        feed.add(post);
                    }
                }
            }

            return feed.stream().map(this::convertPostToPostDTO).collect(Collectors.toList());

        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
            throw new DataSourceLookupFailureException("Can't retrieve objects from DB");
        }
    }

    private Post buildPostFromRequest(PostCreateRequest postCreateRequest) {
        try {
            Post builtPost = new Post();
            builtPost.setHeader(Optional.ofNullable(postCreateRequest.getHeader())
                    .orElseThrow(FieldCantBeEmptyException::new));
            builtPost.setMessage(Optional.ofNullable(postCreateRequest.getMessage())
                    .orElseThrow(FieldCantBeEmptyException::new));
            builtPost.setCreationDateTime(ZonedDateTime.now());
            builtPost.setAuthorId(Optional.ofNullable(postCreateRequest.getAuthorId())
                    .orElseThrow(FieldCantBeEmptyException::new));

            return builtPost;

        }
        catch (Exception ex) {
            log.error("Bad request " + postCreateRequest.toString());
            throw new FieldCantBeEmptyException();
        }
    }

    private PostDTO convertPostToPostDTO(Post post) {
        try {
            PostDTO postDTO = new PostDTO();
            postDTO.setPostId(Optional.ofNullable(post.getPostId()).orElseThrow(FieldCantBeEmptyException::new));
            postDTO.setHeader(Optional.ofNullable(post.getHeader()).orElseThrow(FieldCantBeEmptyException::new));
            postDTO.setMessage(Optional.ofNullable(post.getMessage()).orElseThrow(FieldCantBeEmptyException::new));
            postDTO.setAuthorId(Optional.ofNullable(post.getAuthorId()).orElseThrow(FieldCantBeEmptyException::new));

            return postDTO;

        }
        catch (Exception ex) {
            log.error("Wrong post " + post.toString());
            throw new FieldCantBeEmptyException();
        }
    }
}
