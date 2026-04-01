package com.example.study.domain.user.dto;

import com.example.study.domain.user.entity.User;

public record UserResponseDto(
    String email,
    String name
) {
  public UserResponseDto(User user) {
    this(user.getEmail(), user.getName());
  }
}
