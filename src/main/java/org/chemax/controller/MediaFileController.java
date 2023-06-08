package org.chemax.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.chemax.entity.MediaFile;
import org.chemax.request.MediaFileCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Контроллер медиафайлов", description = "Уровень доступа USER")
@RequestMapping(value = "/media")
public interface MediaFileController {

    @Operation(summary = "Загрузка медиафайла",
               description = "сохраняет медиафайл в базе, сериализуя его в массив байт")
    @PostMapping(value = "/create")
    ResponseEntity<MediaFile> createMediaFile(@RequestBody @Parameter(description = "Принимает json-объект, содержащий"
                                            + " имя файла, сериализуемый объект MultipartFile и Id поста, к которому " +
                                            "прикрепляется медиафайл", required = true)
                                            MediaFileCreateRequest mediaFileCreateRequest);
}
