package org.chemax.request;

import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.Map;

public class PostUpdateRequest {
    private String header;
    private String message;
    private Map<String, MultipartFile> files;
    private ZonedDateTime updatedDateTime;

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

    public Map<String, MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(Map<String, MultipartFile> files) {
        this.files = files;
    }

    public ZonedDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(ZonedDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}
