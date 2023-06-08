package org.chemax.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.chemax.entity.User;
import org.chemax.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Админский контроллер", description = "Доступ в админку. Уровень доступа ADMIN")
@RequestMapping("/admin/user")
public interface AdminController {

    @Operation(summary = "Все пользователи",
               description = "Возвращает лист всех зарегистрированных в системе пользователей")
    @GetMapping("/list")
    ResponseEntity<List<User>> getUserList();

    @Operation(summary = "Удаление пользователей",
               description = "Удаляет пользователя из базы данных, принимает в качестве параметра Id пользователя")
    @DeleteMapping(value = "/delete")
    ResponseEntity<Void> deleteUserById(@RequestParam @Parameter(description = "Id пользователя в базе", required = true)
                                        Long userId);

    @Operation(summary = "Внесение изменений",
               description = "Вносит изменения в экземпляр уже существующего пользователя")
    @PutMapping(value = "/update")
    ResponseEntity<Void> updateUserById(@RequestParam
                                        @Parameter(description = "Id пользователя в базе", required = true)
                                        Long userId,
                                        @RequestBody
                                        @Parameter(description = "Принимает json-объект, содержащий имя пользователя," +
                                                " email и пароль", required = true)
                                        UserUpdateRequest userUpdateRequest);


}
