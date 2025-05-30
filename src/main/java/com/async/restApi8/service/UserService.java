package com.async.restApi8.service;

import com.async.restApi8.dto.UserRequestDto;
import com.async.restApi8.dto.UserResponseDto;
import com.async.restApi8.entity.User;
import com.async.restApi8.mapper.UserMapper;
import com.async.restApi8.repository.UserRepository;
import jakarta.transaction.Transactional;
import jdk.jshell.spi.ExecutionControl;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByUsername(userRequestDto.username())) {
            throw new ServiceException("Username already exists");
        }

        if (userRepository.existsByEmail(userRequestDto.email())) {
            throw new ServiceException("Email already exists");
        }

            User user = new User();
            user.setUsername(userRequestDto.username());
            user.setPassword(new BCryptPasswordEncoder(5).encode(userRequestDto.password()));
            user.setEmail(userRequestDto.email());
            user.setRole("ROLE_USER");
            return UserMapper.userToDto(userRepository.save(user));
    }
/*
    public UserResponseDto getUserByUsernameOrEmail(String query) {     TODO идея классная, запомнить
        if (!userRepository.existsByUsername(query)) {
            throw new ServiceException("Username not found");
        }
            return UserMapper.userToDto(userRepository.findByUsernameOrEmail(query));
    }
*/
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException("This ID not found"));
        return UserMapper.userToDto(user);
    }

    public UserResponseDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ServiceException("This username not found"));
        return UserMapper.userToDto(user);
    }

    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ServiceException("This email not found"));
        return UserMapper.userToDto(user);
    }

    public UserResponseDto deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException("This ID not found"));
        userRepository.delete(user);
        return UserMapper.userToDto(user);
    }

    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException("This ID not found"));

        user.setUsername(userRequestDto.username());
        user.setPassword(new BCryptPasswordEncoder(5).encode(userRequestDto.password()));
        user.setEmail(userRequestDto.email());
        user.setRole("ROLE_USER");
        return UserMapper.userToDto(userRepository.save(user));
    }
}
