package com.async.restApi8.service;

import com.async.restApi8.dto.UserRequestDto;
import com.async.restApi8.dto.UserResponseDto;
import com.async.restApi8.entity.User;
import com.async.restApi8.mapper.UserMapper;
import com.async.restApi8.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.username());
        user.setPassword(new BCryptPasswordEncoder(5).encode(userRequestDto.password()));
        user.setEmail(userRequestDto.email());
        user.setRole("ROLE_USER");
        return UserMapper.userToDto(userRepository.save(user));
    }
}
