package com.example.study.domain.auth.dto.response;

import com.example.study.domain.user.entity.User;

public record LoginResponse(
    String email,
    String name,
    String accessToken
) {
  public LoginResponse(User user, String accessToken) {
    this(user.getEmail(), user.getName(), accessToken);
  }
}
