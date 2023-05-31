package org.chemax.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Map;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

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

    @Column(name = "image_path")
    private String uploadPath;

    @Transient
    private Map<String, MultipartFile> files;

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

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public Map<String, MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(Map<String, MultipartFile> files) {
        this.files = files;
    }
}
