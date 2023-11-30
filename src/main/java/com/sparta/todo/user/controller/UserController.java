package com.sparta.todo.user.controller;

import com.sparta.todo.user.dto.SignupRequestDto;
import com.sparta.todo.user.entity.User;
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
@RequestMapping("/api/todo")
public class UserController {

  private final UserService userService;

  // 회원 가입
  @PostMapping("/signup")
  public ResponseEntity<User> signup(@Valid @RequestBody SignupRequestDto requestDto) {
    User user = userService.signup(requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }
}
