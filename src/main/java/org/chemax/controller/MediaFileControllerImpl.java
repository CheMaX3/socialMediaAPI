package org.chemax.controller;

import org.chemax.entity.MediaFile;
import org.chemax.request.MediaFileCreateRequest;
import org.chemax.service.MediaFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaFileControllerImpl implements MediaFileController {

    private final MediaFileService mediaFileService;

    public MediaFileControllerImpl(MediaFileService mediaFileService) {
        this.mediaFileService = mediaFileService;
    }

    @Override
    public ResponseEntity<MediaFile> createMediaFile(MediaFileCreateRequest mediaFileCreateRequest) {
        final MediaFile mediaFile = mediaFileService.createMediaFile(mediaFileCreateRequest);
        return new ResponseEntity<>(mediaFile, HttpStatus.CREATED);
    }
}
