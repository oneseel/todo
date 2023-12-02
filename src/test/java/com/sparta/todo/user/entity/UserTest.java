package com.sparta.todo.user.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.todo.user.dto.UserSignupRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserTest {

  @Test
  @DisplayName("User 엔터티 생성 - 성공")
  void createUserTest() {

    // given
    UserSignupRequestDto requestDto = new UserSignupRequestDto();
    requestDto.setUsername("username");
    requestDto.setPassword("password");

    // when
    User user = new User(requestDto);

    // then
    assertEquals("username", user.getUsername());
    assertEquals("password", user.getPassword());
  }

}