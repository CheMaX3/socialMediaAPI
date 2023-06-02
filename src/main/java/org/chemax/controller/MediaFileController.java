package org.chemax.controller;

import org.chemax.entity.MediaFile;
import org.chemax.request.MediaFileCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//TODO:Swagger annotations

@RequestMapping(value = "/media")
public interface MediaFileController {

    @PostMapping(value = "/create")
    ResponseEntity<MediaFile> createMediaFile(@RequestBody MediaFileCreateRequest mediaFileCreateRequest);

}
