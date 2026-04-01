package com.example.study.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(

    @NotBlank(message = "이메일은 필수로 입력하셔야 합니다.")
    @Email(message = "올바르지 않은 이메일 형식입니다.")
    String email,

    @NotBlank(message = "비밀번호는 필수로 입력하셔야 합니다.")
    @Size(min = 8, max = 50, message = "비밀번호는 8자 이상 50자 이하로만 가능합니다.")
    String password,

    @NotBlank(message = "이름은 필수로 입력하셔야 합니다.")
    @Size(min = 2, max = 10, message = "이름은 2자 이상 10자 이하로만 가능합니다.")
    String name
) { }
