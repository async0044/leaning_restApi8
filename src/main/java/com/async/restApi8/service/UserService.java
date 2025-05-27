package com.async.restApi8.service;

import com.async.restApi8.dto.UserRequestDto;
import com.async.restApi8.dto.UserResponseDto;
import com.async.restApi8.entity.User;
import com.async.restApi8.mapper.UserMapper;
import com.async.restApi8.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        try {
            User user = new User();
            user.setUsername(userRequestDto.username());
            user.setPassword(new BCryptPasswordEncoder(5).encode(userRequestDto.password()));
            user.setEmail(userRequestDto.email());
            user.setRole("ROLE_USER");
            return UserMapper.userToDto(userRepository.save(user));
        }
        catch (Exception e) {
            throw new ServiceException("Failed to add user", e);
        }
    }

    public UserResponseDto getUser(String query) {
        try {
            return UserMapper.userToDto(userRepository.findByUsernameOrEmailCaseInsensitive(query));
        }
        catch (Exception e) {
            throw new ServiceException("Failed to get user", e);
        }
    }

    public UserResponseDto deleteUserById(Long id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            UserResponseDto userResponseDto = UserMapper.userToDto(user);
            userRepository.delete(user);
            return userResponseDto;
        }
        catch (Exception e) {
            throw new ServiceException("Failed to delete user", e);
        }
    }

    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new ServiceException("User not found. " + id));
            user.setUsername(userRequestDto.username());
            user.setPassword(new BCryptPasswordEncoder(5).encode(userRequestDto.password()));
            user.setEmail(userRequestDto.email());
            user.setRole("ROLE_USER");
            return UserMapper.userToDto(userRepository.save(user));
        }
        catch (Exception e) {
            throw new ServiceException("Failed to update user", e);
        }
    }
}
