package com.sparta.todo.user.dto;

import com.sparta.todo.user.entity.User;

public record UserResponseDto(
    Long id,
    String username
) {

  public UserResponseDto(User user) {
    this(user.getId(), user.getUsername());
  }
}
