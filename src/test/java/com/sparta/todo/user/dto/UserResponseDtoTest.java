package com.sparta.todo.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.todo.user.entity.User;
import org.junit.jupiter.api.Test;

class UserResponseDtoTest {

  @Test
  void userResponseDtoTest() {
    // given
    User user = new User();
    user.setId(1L);
    user.setUsername("username");

    // when
    UserResponseDto responseDto = new UserResponseDto(user);

    // then
    assertEquals(user.getId(), responseDto.getId());
    assertEquals(user.getUsername(), responseDto.getUsername());
  }

}