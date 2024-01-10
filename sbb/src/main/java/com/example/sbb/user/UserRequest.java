package com.example.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter
    @Setter
    public static class SignupDTO {
        @Size(min = 4, max = 20, message = "username must be between 4 and 20 characters")
        @NotNull(message = "username is required")
        private String username;

        @NotNull(message = "password is required")
        private String password;

        @NotNull(message = "email is required")
        @Email
        private String email;

        @NotNull(message = "nickname is required")
        private String nickname;

        @NotNull(message = "role is required")
        private Role role;

        public User toEntity() {
            return User.builder()
                    .name(username)
                    .password(password)
                    .email(email)
                    .nickName(nickname)
                    .role(role)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class LoginDTO {

        @NotNull(message = "email is required")
        @Email
        private String email;

        @NotNull(message = "password is required")
        private String password;

        public LoginDTO(String email, String password) {
            this.email = email;
            this.password = password;
        }

    }
}
