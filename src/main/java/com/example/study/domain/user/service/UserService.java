package com.example.study.domain.user.service;

import com.example.study.domain.user.dto.UserRequestDto;
import com.example.study.domain.user.dto.UserResponseDto;
import com.example.study.domain.user.entity.User;
import com.example.study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional(readOnly = false)
  public UserResponseDto createUser(UserRequestDto userRequestDto) {
    String encodedPassword = passwordEncoder.encode(userRequestDto.password());

    User user = User.createUser(userRequestDto.email(), encodedPassword, userRequestDto.name());

    User create = userRepository.save(user);

    return new UserResponseDto(create);
  }

  public UserResponseDto getUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

    return new UserResponseDto(user);
  }

  @Transactional(readOnly = false)
  public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

    String encodedPassword = passwordEncoder.encode(userRequestDto.password());

    user.updateUser(userRequestDto.email(), encodedPassword, userRequestDto.name());

    return new UserResponseDto(user);
  }

  @Transactional(readOnly = false)
  public void deleteUser(Long id) {
    if(!userRepository.existsById(id)) {
      throw new IllegalArgumentException("존재하지 않는 회원입니다.");
    }

    userRepository.deleteById(id);
  }
}
