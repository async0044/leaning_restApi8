package com.async.restApi8.controller;

import com.async.restApi8.dto.ItemDto;
import com.async.restApi8.entity.Item;
import com.async.restApi8.mapper.ItemMapper;
import com.async.restApi8.service.ItemService;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ItemController.class)
public class ItemControllerRestTest {

         /*
    addNewItem      +
    getItemById     +
    getAllItems     +
    modifyItemById  +
    deleteItemById  +
     */

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ItemService itemService;

    //--------------    addNewItem

    @Test
    public void addNewItem_ExpectSuccessMessage() throws Exception {
        Long testId = 1L;
        //Item testItem = new Item("TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR");
        ItemDto testItemDto = new ItemDto(testId, "TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR");

        when(itemService.addItem(any(Item.class))).thenReturn(testItemDto);

        mockMvc.perform(put("/item/addItem")
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
        when(itemService.addItem(any(Item.class))).thenThrow(ServiceException.class);

        mockMvc.perform(put("/item/addItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"TEST-TITLE\",\"content\":\"TEST-CONTENT\",\"author\":\"TEST-AUTHOR\"}"))
                .andExpect(status().isInternalServerError());

    }

    //------------- getItemById

    @Test
    public void getItemById_ExpectSucess() throws Exception {
        Long testId = 1L;
        ItemDto testDto = new ItemDto(testId, "TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR");

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
        List<ItemDto> testList = new ArrayList<>();
        testList.add(new ItemDto(1L, "TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR"));
        testList.add(new ItemDto(2L, "TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR"));
        testList.add(new ItemDto(3L, "TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR"));

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
