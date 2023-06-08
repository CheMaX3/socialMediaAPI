package org.chemax.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Schema(description = "Запрос на изменение пользователя")
public class UserUpdateRequest {

    @Size(min = 4, max = 20, message = "Имя пользователя не может быть короче 4 символов и длиннее 20 символов")
    @Schema(description = "Имя пользователя/логин", example = "Vasilii")
    private String username;

    @Size(min = 4, max = 50, message = "Электронная почта не может быть короче 4 символов и длиннее 50 символов")
    @Email
    @Schema(description = "Электронная почта", example = "vasiliiTheBest@mail.ru")
    private String email;


    @Schema(description = "Пароль", example = "12345")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
