package com.sparta.todo.user.dto;

import com.sparta.todo.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

  private final Long id;
  private final String username;

  public UserResponseDto(User savedUser) {
    this.id = savedUser.getId();
    this.username = savedUser.getUsername();
  }
}