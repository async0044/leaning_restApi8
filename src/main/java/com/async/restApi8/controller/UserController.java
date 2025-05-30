package com.async.restApi8.controller;

import com.async.restApi8.dto.UserRequestDto;
import com.async.restApi8.dto.UserResponseDto;
import com.async.restApi8.entity.User;
import com.async.restApi8.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserResponseDto> addUser(@RequestBody @Valid UserRequestDto userRequestDto) {
            return ResponseEntity.ok().body(userService.addUser(userRequestDto));
    }
/*
    @GetMapping("/getUser")
    public ResponseEntity<UserResponseDto> getUser(@RequestParam @Valid String query) {
            return ResponseEntity.ok(userService.getUser(query));   TODO идея классная, запомнить
    }
*/
    @GetMapping("/getUserById")
    public ResponseEntity<UserResponseDto> getUserById(@RequestParam("id") Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping("/getUserByUsername")
    public ResponseEntity<UserResponseDto> getUserByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok().body(userService.getUserByEmail(email));
    }

    @DeleteMapping("/deleteUserById")
    public ResponseEntity<UserResponseDto> deleteUserById(@RequestParam("id") Long id) {
        return ResponseEntity.ok().body(userService.deleteUserById(id));
    }



    @PatchMapping("/updateUserById")
    public ResponseEntity<UserResponseDto> updateUser(@RequestParam("id") Long id, @RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUserById(id, userRequestDto));
    }






}
