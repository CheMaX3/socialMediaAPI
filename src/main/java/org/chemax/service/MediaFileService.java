package org.chemax.service;

import org.chemax.entity.MediaFile;
import org.chemax.request.MediaFileCreateRequest;
import org.chemax.request.PostCreateRequest;

public interface MediaFileService {

    MediaFile createMediaFile(MediaFileCreateRequest mediaFileCreateRequest);



}
