package com.sparta.todo.user.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;

import com.sparta.todo.user.dto.UserResponseDto;
import com.sparta.todo.user.dto.UserSignupRequestDto;
import com.sparta.todo.user.entity.User;
import com.sparta.todo.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private UserService userService;

  @Test
  @DisplayName("회원가입 - 성공")
  void test1() {

    // Given
    UserSignupRequestDto requestDto = new UserSignupRequestDto();
    requestDto.setUsername("test123");
    requestDto.setPassword("test123");

    given(userRepository.findByUsername(requestDto.getUsername())).willReturn(Optional.empty());

    given(passwordEncoder.encode(requestDto.getPassword())).willReturn("encodedPassword");

    given(userRepository.save(any(User.class))).willAnswer(invocation -> {
      User savedUser = invocation.getArgument(0);
      savedUser.setId(100L); // 예시로 ID를 설정
      return savedUser;
    });

    // When
    UserResponseDto responseDto = userService.signup(requestDto);

    // Then
    assertEquals("test123", responseDto.getUsername());
    assertNotNull(responseDto.getId());
  }

  @Test
  @DisplayName("회원가입 - 실패(중복된 사용자)")
  void test2() {

    // Given
    UserSignupRequestDto requestDto = new UserSignupRequestDto();
    requestDto.setUsername("test123");
    requestDto.setPassword("test123");

    User user = new User(requestDto.getUsername(), requestDto.getPassword());
    given(userRepository.findByUsername(requestDto.getUsername())).willReturn(Optional.of(user));

    // When & Then
    IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> {
      userService.signup(requestDto);
    });

    assertEquals("중복된 회원이 존재합니다.", thrownException.getMessage());
  }

}
