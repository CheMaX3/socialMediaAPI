package org.chemax.service;

import org.chemax.entity.MediaFile;
import org.chemax.request.MediaFileCreateRequest;

public interface MediaFileService {

    MediaFile createMediaFile(MediaFileCreateRequest mediaFileCreateRequest);
}
