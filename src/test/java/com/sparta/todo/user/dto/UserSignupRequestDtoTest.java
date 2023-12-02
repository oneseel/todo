package com.sparta.todo.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class UserSignupRequestDtoTest {

  @Test
  void userSignupRequestDtoTest() {
    UserSignupRequestDto requestDto = new UserSignupRequestDto();
    requestDto.setUsername("username");
    requestDto.setPassword("password");

    assertNotNull(requestDto.getUsername());
    assertNotNull(requestDto.getPassword());
    assertEquals("username", requestDto.getUsername());
    assertEquals("password", requestDto.getPassword());
  }
}