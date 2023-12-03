package com.sparta.todo.user.service;

import com.sparta.todo.user.dto.UserResponseDto;
import com.sparta.todo.user.dto.UserSignupRequestDto;
import com.sparta.todo.user.entity.User;
import com.sparta.todo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  // 회원 가입
  public UserResponseDto signup(UserSignupRequestDto requestDto) {
    String username = requestDto.getUsername();
    String password = passwordEncoder.encode(requestDto.getPassword());

    // 회원 중복 확인
    userRepository.findByUsername(username).ifPresent(user -> {
      throw new IllegalArgumentException("중복된 회원이 존재합니다.");
    });

    // 회원 등록
    User user = new User(requestDto);

    User saveUser = userRepository.save(user);

    return new UserResponseDto(saveUser);
  }
}