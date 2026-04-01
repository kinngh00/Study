package com.example.study.domain.auth.service;

import com.example.study.domain.auth.dto.request.LoginRequest;
import com.example.study.domain.auth.dto.response.LoginResponse;
import com.example.study.domain.user.entity.User;
import com.example.study.domain.user.repository.UserRepository;
import com.example.study.global.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  public LoginResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.email().trim().toLowerCase())
        .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다."));

    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
      throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
    }

    String accessToken = jwtTokenProvider.generateToken(user.getEmail(), user.getRole().name());
    return new LoginResponse(user, accessToken);
  }
}
