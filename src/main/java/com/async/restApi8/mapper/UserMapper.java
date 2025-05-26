package com.async.restApi8.mapper;

import com.async.restApi8.dto.UserRequestDto;
import com.async.restApi8.dto.UserResponseDto;
import com.async.restApi8.entity.User;

public class UserMapper {

    public static UserResponseDto userToDto(User user) {
        if (user == null) return null;
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public static User dtoToUser(UserRequestDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setUsername(dto.username());           //ХБЗ НАДО ЭТО ИЛИ НЕТ. ПОДУМАТЬ
        user.setEmail(dto.email());         //СЕЙЧАС ВСЁ ДЕЛАЕТ USERSERVICE
        return user;
    }
}
