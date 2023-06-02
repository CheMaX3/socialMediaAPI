package org.chemax.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "mediafiles")
public class MediaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "filename")
    private String filename;

    @Column(name = "content")
    private byte[] content;

    @Column(name = "post_id")
    private Long postId;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "MediaFile{" +
                "fileId=" + fileId +
                ", filename='" + filename + '\'' +
                ", content=" + Arrays.toString(content) +
                ", postId=" + postId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaFile mediaFile = (MediaFile) o;
        return Objects.equals(fileId, mediaFile.fileId) && Objects.equals(filename, mediaFile.filename) && Arrays.equals(content, mediaFile.content) && Objects.equals(postId, mediaFile.postId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fileId, filename, postId);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }
}
