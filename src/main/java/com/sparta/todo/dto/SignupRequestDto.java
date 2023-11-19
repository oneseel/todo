package com.sparta.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 4, max = 10, message = "아이디는 최소 4자에서 최대 10자여야 합니다.")
    @Pattern(regexp = "^[a-z0-9]+$",
            message = "아이디는 알파벳 소문자(a~z)와 숫자(0~9)로만 구성되어야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 최소 8자에서 최대 15자여야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$",
            message = "비밀번호는 알파벳 대소문자와 숫자를 모두 포함해야 합니다.")
    private String password;
}
