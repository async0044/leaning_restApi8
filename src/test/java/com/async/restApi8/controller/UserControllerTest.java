package com.async.restApi8.controller;

import com.async.restApi8.dto.ItemRequestDto;
import com.async.restApi8.dto.UserRequestDto;
import com.async.restApi8.dto.UserResponseDto;
import com.async.restApi8.security.TestSecurityConfig;
import com.async.restApi8.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@Import(TestSecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    /*
    addUser
    getUser
    deleteUser
    patchUserById
     */

    @Test
    public void addUser_expectOk() throws Exception {
        Long testId = 1L;
        UserResponseDto userResponseDto = new UserResponseDto(testId, "TEST_USERNAME", "TEST_EMAIL", "ROLE_USER");

        when(userService.addUser(any(UserRequestDto.class))).thenReturn(userResponseDto);

        mockMvc.perform(post("/user/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"TEST_USERNAME\",\"password\":\"TEST_PASSWORD\",\"email\":\"TEST_EMAIL\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(testId))
                .andExpect(jsonPath("$.username").value("TEST_USERNAME"))
                .andExpect(jsonPath("$.email").value("TEST_EMAIL"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));
    }

    @Test
    public void addUser_expectNotFound() throws Exception {
        when(userService.addUser(any(UserRequestDto.class))).thenThrow(new ServiceException("Failed to add user"));;

        mockMvc.perform(post("/user/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"TEST_USERNAME\",\"password\":\"TEST_PASSWORD\",\"email\":\"TEST_EMAIL\"}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void getUser_expectOk() throws Exception {
        Long testId = 1L;
        UserResponseDto userResponseDto = new UserResponseDto(testId, "TEST_USERNAME", "TEST_EMAIL", "ROLE_USER");

        when(userService.getUser("someUser")).thenReturn(userResponseDto);

        mockMvc.perform(get("/user/getUser")
                .contentType(MediaType.APPLICATION_JSON)
                .param("query", "someUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId))
                .andExpect(jsonPath("$.username").value("TEST_USERNAME"))
                .andExpect(jsonPath("$.email").value("TEST_EMAIL"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));

    }

    @Test
    public void getUser_expectNotFound() throws Exception {
        when(userService.getUser("someUser")).thenThrow(new UsernameNotFoundException("User not found"));;

        mockMvc.perform(get("/user/getUser")
                .contentType(MediaType.APPLICATION_JSON)
                .param("query", "someUser"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteUser_expectOk() throws Exception {
        Long testId = 1L;
        UserResponseDto userResponseDto = new UserResponseDto(testId, "TEST_USERNAME", "TEST_EMAIL", "ROLE_USER");

        when(userService.deleteUserById(testId)).thenReturn(userResponseDto);

        mockMvc.perform(delete("/user/deleteUserById")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", testId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId))
                .andExpect(jsonPath("$.username").value("TEST_USERNAME"))
                .andExpect(jsonPath("$.email").value("TEST_EMAIL"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));
    }

    @Test
    public void deleteUser_expectNotFound() throws Exception {
        Long testId = 1L;

        when(userService.deleteUserById(testId)).thenThrow(new ServiceException("User not found"));

        mockMvc.perform(delete("/user/deleteUserById")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", testId.toString()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void updateUserById_expectOk() throws Exception {
        Long testId = 1L;
        UserRequestDto userRequestDto = new UserRequestDto("TEST_USERNAME", "TEST_PASSWORD", "TEST_EMAIL");
        UserResponseDto userResponseDto = new UserResponseDto(testId, "TEST_USERNAME", "TEST_EMAIL", "ROLE_USER");

        when(userService.updateUserById(testId, userRequestDto)).thenReturn(userResponseDto);

        mockMvc.perform(patch("/user/updateUserById")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", testId.toString())
                .content("{\"username\":\"TEST_USERNAME\",\"password\":\"TEST_PASSWORD\",\"email\":\"TEST_EMAIL\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId))
                .andExpect(jsonPath("$.username").value("TEST_USERNAME"))
                .andExpect(jsonPath("$.email").value("TEST_EMAIL"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));
    }

    @Test
    public void updateUserById_expectNotFound() throws Exception {
        Long testId = 1L;
        UserRequestDto userRequestDto = new UserRequestDto("TEST_USERNAME", "TEST_PASSWORD", "TEST_EMAIL");

        when(userService.updateUserById(testId, userRequestDto)).thenThrow(new ServiceException("Failed to update user"));

        mockMvc.perform(patch("/user/updateUserById")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", testId.toString())
                .content("{\"username\":\"TEST_USERNAME\",\"password\":\"TEST_PASSWORD\",\"email\":\"TEST_EMAIL\"}"))
                .andExpect(status().isInternalServerError());
    }
}
