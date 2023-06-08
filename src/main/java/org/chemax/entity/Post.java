package org.chemax.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Size(max = 255, message = "Заголовок не может быть длиннее 255 символов")
    @Column(name = "header")
    private String header;

    @Column(name = "message")
    private String message;

    @Column(name = "creation_date_time")
    private ZonedDateTime creationDateTime;

    @Column(name = "updated_date_time")
    private ZonedDateTime updatedDateTime;

    @Column(name = "author_id")
    private Long authorId;

    @Transient
    private List<MediaFile> mediaFiles;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(ZonedDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public ZonedDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(ZonedDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public List<MediaFile> getMediaFiles() {
        return mediaFiles;
    }

    public void setMediaFiles(List<MediaFile> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", header='" + header + '\'' +
                ", message='" + message + '\'' +
                ", creationDateTime=" + creationDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", authorId=" + authorId +
                ", mediaFiles=" + mediaFiles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postId, post.postId) && Objects.equals(header, post.header) && Objects.equals(message, post.message) && Objects.equals(creationDateTime, post.creationDateTime) && Objects.equals(updatedDateTime, post.updatedDateTime) && Objects.equals(authorId, post.authorId) && Objects.equals(mediaFiles, post.mediaFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, header, message, creationDateTime, updatedDateTime, authorId, mediaFiles);
    }
}
