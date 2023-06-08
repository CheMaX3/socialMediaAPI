package org.chemax.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.chemax.request.UserCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Контроллер регистрации", description = "Обслуживает запросы на регистрацию. Уровень доступа UNAUTHORIZED")
@RequestMapping(value = "/registration/user")
public interface RegistrationController {

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping(value = "/create")
    ResponseEntity<Void> createUser(@RequestBody
                                    @Parameter(description = "Принимает json-объект, содержащий имя пользователя/логин" +
                                            "электронную почту и пароль. Пароль сохраняется в базе в хэшированном виде")
                                    UserCreateRequest userCreateRequest);
}
