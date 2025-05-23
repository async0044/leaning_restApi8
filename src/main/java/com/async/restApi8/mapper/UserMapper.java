package com.async.restApi8.mapper;

import com.async.restApi8.dto.UserDto;
import com.async.restApi8.entity.User;

public class UserMapper {

    public static UserDto userToUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword());
    }
}
