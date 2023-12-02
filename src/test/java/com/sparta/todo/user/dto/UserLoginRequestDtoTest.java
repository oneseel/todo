package com.sparta.todo.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class UserLoginRequestDtoTest {

  @Test
  void userLoginRequestDtoTest() {

    UserLoginRequestDto requestDto = new UserLoginRequestDto();
    requestDto.setUsername("username");
    requestDto.setPassword("password");

    assertNotNull(requestDto.getUsername());
    assertNotNull(requestDto.getPassword());
    assertEquals("username", requestDto.getUsername());
    assertEquals("password", requestDto.getPassword());
  }

}