package com.sparta.todo.user.controller;

import com.sparta.todo.user.dto.UserResponseDto;
import com.sparta.todo.user.dto.UserSignupRequestDto;
import com.sparta.todo.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  // 회원 가입
  @PostMapping("/signup")
  public ResponseEntity<UserResponseDto> signup(
      @Valid @RequestBody UserSignupRequestDto requestDto) {
    UserResponseDto userResponseDto = userService.signup(requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
  }
}
