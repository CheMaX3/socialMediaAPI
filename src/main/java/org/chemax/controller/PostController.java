package org.chemax.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.chemax.dto.PostDTO;
import org.chemax.request.PostCreateRequest;
import org.chemax.request.PostUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Контроллер постов", description = "Предоставляет доступ к функционалу постов. Уровень доступа USER")
@RequestMapping(value = "/post")
public interface PostController {

    @Operation(summary = "Создание поста")
    @PostMapping(value = "/create")
    ResponseEntity<PostDTO> createPost(@RequestBody
                                       @Parameter(description = "Принимает json-объект, содержащий заголовок поста," +
                                               "текст поста и Id автора поста", required = true)
                                       PostCreateRequest postCreateRequest);

    @Operation(summary = "Запрос поста", description = "Возвращает DTO поста по его Id")
    @GetMapping(value = "/get")
    ResponseEntity<PostDTO> getPostById(@RequestParam @Parameter(description = "Id поста", required = true)
                                        Long postId);

    @Operation(summary = "Удаление поста", description = "Удаляет пост по его Id")
    @DeleteMapping(value = "/delete")
    ResponseEntity<Void> deletePostById(@RequestParam @Parameter(description = "Id поста", required = true)
                                        Long postId);

    @Operation(summary = "Изменение поста", description = "Изменяет содержимое поста")
    @PutMapping(value = "/update")
    ResponseEntity<PostDTO> updatePostById(@RequestParam
                                           @Parameter(description = "Id изменяемого поста", required = true)
                                           Long postId,
                                           @RequestBody
                                           @Parameter(description = "Принимает json-объект, содержащий заголовок " +
                                                   "поста и текст поста", required = true)
                                           PostUpdateRequest postUpdateRequest);

    @Operation(summary = "Запрос листа постов", description = "Возвращает лист всех постов автора по его Id")
    @GetMapping(value = "get_posts_by_author")
    ResponseEntity<List<PostDTO>> getPostsByAuthorId(@RequestParam
                                                     @Parameter(description = "Id автора постов")
                                                     Long authorId);

    @Operation(summary = "Лента активности", description = "Возвращает лист с необходимым количеством постов. " +
                                             "Поддерживает пагинацию. Принимает в адресной строке Id пользователя, " +
                                             "для которого нужно вывести посты пользователей, на которых он подписан," +
                                             " номер страницы и нужное количество постов на странице. Посты выводятся" +
                                             " отсортированными по дате создания от самых новых к самым старым")
    @GetMapping(value = "get_feed")
    ResponseEntity<List<PostDTO>> getFeedByUserId(@RequestParam
                                                  @Parameter(description = "Id пользователя для которого выводится " +
                                                          "лента активности")
                                                  Long userId,
                                                  @RequestParam
                                                  @Parameter(description = "Номер отображемой страницы")
                                                  Integer pageNumber,
                                                  @RequestParam
                                                  @Parameter(description = "Количество отображаемых на странице постов")
                                                  Integer postCount);
}
