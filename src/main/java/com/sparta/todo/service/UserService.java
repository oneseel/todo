package com.sparta.todo.service;

import com.sparta.todo.dto.LoginRequestDto;
import com.sparta.todo.dto.SignupRequestDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    // 회원 가입
    public User signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 회원이 존재합니다.");
        }

        // 회원 등록
        User user = new User(username, password);

        User saveUser = userRepository.save(user);

        return saveUser;
    }

    // 로그인
    public String login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 회원 중복 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 회원이 존재합니다.")
        );

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getUsername());
        jwtUtil.addJwtToCookie(token, res);

        return token;
    }
}