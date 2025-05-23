package com.async.restApi8.dto;

public class UserDto {

    public Long id;
    public String username;
    public String role;

    public UserDto(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
