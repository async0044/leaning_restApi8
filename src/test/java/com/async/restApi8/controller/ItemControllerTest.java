package com.async.restApi8.controller;

import com.async.restApi8.dto.ItemRequestDto;
import com.async.restApi8.dto.ItemResponseDto;
import com.async.restApi8.service.ItemService;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ItemService itemService;

    //--------------    addNewItem

    @Test                                   //TODO  РАЗБИРАЕМСЯ С АВТОРИЗАЦИЕЙ В ТЕСТАХ
    @WithMockUser
    public void addNewItem_ExpectSuccessMessage() throws Exception {
        Long testId = 1L;
        ItemResponseDto testItemResponseDto = new ItemResponseDto(testId, "TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR");

        when(itemService.addItem(any(ItemRequestDto.class))).thenReturn(testItemResponseDto);

        mockMvc.perform(put("/item/addItem")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"TEST-TITLE\",\"content\":\"TEST-CONTENT\",\"author\":\"TEST-AUTHOR\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId))
                .andExpect(jsonPath("$.title").value("TEST-TITLE"))
                .andExpect(jsonPath("$.content").value("TEST-CONTENT"))
                .andExpect(jsonPath("$.author").value("TEST-AUTHOR"));
    }

    @Test
    public void addNewItem_ExpectErrorMessage() throws Exception {
        when(itemService.addItem(any(ItemRequestDto.class))).thenThrow(ServiceException.class);

        mockMvc.perform(put("/item/addItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"TEST-TITLE\",\"content\":\"TEST-CONTENT\",\"author\":\"TEST-AUTHOR\"}"))
                .andExpect(status().isInternalServerError());

    }

    //------------- getItemById

    @Test
    public void getItemById_ExpectSucess() throws Exception {
        Long testId = 1L;
        ItemResponseDto testDto = new ItemResponseDto(testId, "TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR");

        when(itemService.getItemById(testId)).thenReturn(testDto);

        mockMvc.perform(get("/item/getItemById/{id}", testId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId))
                .andExpect(jsonPath("$.title").value("TEST-TITLE"))
                .andExpect(jsonPath("$.content").value("TEST-CONTENT"))
                .andExpect(jsonPath("$.author").value("TEST-AUTHOR"));
    }

    @Test
    public void getItemById_ExpectErrorMessage() throws Exception {
        when(itemService.getItemById(1L)).thenThrow(ServiceException.class);

        mockMvc.perform(get("/item/getItemById/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void getAllItems_ExpectSucess() throws Exception {
        List<ItemResponseDto> testList = new ArrayList<>();
        testList.add(new ItemResponseDto(1L, "TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR"));
        testList.add(new ItemResponseDto(2L, "TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR"));
        testList.add(new ItemResponseDto(3L, "TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR"));

        when(itemService.getAllItems()).thenReturn(testList);

        mockMvc.perform(get("/item/getAllItems")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("TEST-TITLE"))
                .andExpect(jsonPath("$[0].content").value("TEST-CONTENT"))
                .andExpect(jsonPath("$[0].author").value("TEST-AUTHOR"));
    }

    @Test
    public void getAllItems_ExpectErrorMessage() throws Exception {

    }

}
