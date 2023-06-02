package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.entity.MediaFile;
import org.chemax.repository.MediaFileRepository;
import org.chemax.request.MediaFileCreateRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MediaFileServiceImpl implements MediaFileService {

    private static final Logger log = Logger.getLogger(MediaFileServiceImpl.class.getName());
    private final MediaFileRepository mediaFileRepository;

    public MediaFileServiceImpl(MediaFileRepository mediaFileRepository) {
        this.mediaFileRepository = mediaFileRepository;
    }

    @Override
    public MediaFile createMediaFile(MediaFileCreateRequest mediaFileCreateRequest) {
        MediaFile mediaFile = new MediaFile();
        try {
            mediaFile = mediaFileRepository.save(buildMediaFileFromRequest(mediaFileCreateRequest));
        }
        catch (Exception ex) {
            log.error("Can't save object " + mediaFileCreateRequest.toString());
        }
        return mediaFile;
    }

    private MediaFile buildMediaFileFromRequest(MediaFileCreateRequest mediaFileCreateRequest) throws IOException {
        MediaFile builtMediaFile = new MediaFile();
        builtMediaFile.setFilename(mediaFileCreateRequest.getFilename());
        builtMediaFile.setContent(mediaFileCreateRequest.getContent().getBytes());
        builtMediaFile.setPostId(mediaFileCreateRequest.getPostId());
        return builtMediaFile;
    }
}
