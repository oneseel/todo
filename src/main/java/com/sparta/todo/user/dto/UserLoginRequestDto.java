package com.sparta.todo.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginRequestDto {
    @NotBlank
    @Size(min = 4, max = 10)
    String username;

    @NotBlank
    @Size(min = 8, max = 15)
    String password;
}

