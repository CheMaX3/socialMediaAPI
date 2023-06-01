package org.chemax.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Map;

public class PostUpdateRequest {

    @NotNull
    private String header;
    @NotNull
    private String message;

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

    @Override
    public String toString() {
        return "PostUpdateRequest{" +
                "header='" + header + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
