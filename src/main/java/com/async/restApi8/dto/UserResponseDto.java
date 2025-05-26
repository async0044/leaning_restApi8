package com.async.restApi8.dto;

import jakarta.validation.constraints.NotBlank;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String role
) {




/*
    public Long id;
    public String username;
    public String email;
    public String role;
*/
    /*
    - переписать на record
     */
/*
    public UserResponseDto(Long id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    */
}
