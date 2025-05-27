package com.async.restApi8.controller;

import com.async.restApi8.dto.UserRequestDto;
import com.async.restApi8.dto.UserResponseDto;
import com.async.restApi8.entity.User;
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

    @GetMapping("/getUser")
    public ResponseEntity<UserResponseDto> getUser(@RequestParam String query) {
        try {
            return ResponseEntity.ok(userService.getUser(query));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/deleteUserById")
    public ResponseEntity<UserResponseDto> deleteUserById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUserById(id));
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PatchMapping("/updateUserById")
    public ResponseEntity<UserResponseDto> updateUser(@RequestParam Long id, @RequestBody UserRequestDto userRequestDto) {
        try {
            return ResponseEntity.ok(userService.updateUserById(id, userRequestDto));
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }






}
