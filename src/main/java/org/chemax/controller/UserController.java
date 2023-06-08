package org.chemax.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.chemax.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Контроллер пользователей",
     description = "Обслуживает запросы на вывод профиль пользователя. Уровень доступа USER")
@RequestMapping(value = "/user")
public interface UserController {

    @Operation(summary = "Запрос профиля пользователя", description = "Возвращает DTO пользователя")
    @GetMapping(value = "/get")
    ResponseEntity<UserDTO> getUserById(@RequestParam
                                        @Parameter(description = "Принимает Id пользователя, DTO которого нужно вернуть")
                                        Long userId);
}
