package org.chemax.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserCreateRequest {

    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Size(min = 4, max = 20, message = "Имя пользователя не может быть короче 4 символов")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Поле электронная почта не может быть пустым")
    @Size(min = 4, max = 50, message = "Электронная почта не может быть короче 4 символов и длиннее 50 символов")
    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Поле пароля не может быть пустым")
    @Size(min = 4, max = 16, message = "Пароль не может быть короче 4 символов и длиннее 16 символов" )
    @Column(name = "password")
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

    @Override
    public String toString() {
        return "UserCreateRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
