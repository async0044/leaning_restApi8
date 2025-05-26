package com.async.restApi8.controller;

import com.async.restApi8.dto.UserRequestDto;
import com.async.restApi8.dto.UserResponseDto;
import com.async.restApi8.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userRequestDto));
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getUserById")         //сделать несколько getUser с разными переменными
    public UserResponseDto getUserById(@RequestParam int id) {
        return null;
    }

    @GetMapping("/getUserByUsername")
    public UserResponseDto getUserByUsername(@RequestParam String username) {
        return null;
    }

    @GetMapping("/getUserByEmail")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return null;
    }

    @DeleteMapping("/deleteUserById")
    public void deleteUserById(@RequestParam int id) {

    }

    @DeleteMapping("/deleteUserByUsername")
    public void deleteUserByUsername(@RequestParam String username) {

    }

    @PatchMapping("/updateUser")
    public UserResponseDto updateUser(@RequestBody UserResponseDto userResponseDto) {
        return null;
    }






}
