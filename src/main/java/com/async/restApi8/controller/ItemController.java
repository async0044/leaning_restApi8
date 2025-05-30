package com.async.restApi8.controller;

import com.async.restApi8.dto.ItemRequestDto;
import com.async.restApi8.dto.ItemResponseDto;
import com.async.restApi8.entity.Item;
import com.async.restApi8.service.ItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping("/addItem")
    public ResponseEntity<ItemResponseDto> addItem(@RequestBody @Valid ItemRequestDto itemRequestDto) {
        return ResponseEntity.ok().body(itemService.addItem(itemRequestDto));
    }

    @GetMapping("/getItemById")
    public ResponseEntity<ItemResponseDto> getItemById(@RequestParam @NotNull Long id) {
            return ResponseEntity.ok(itemService.getItemById(id));
    }



    @GetMapping("/getAllItems")
    public ResponseEntity<List<ItemResponseDto>> getAllItems() {
            return ResponseEntity.ok(itemService.getAllItems());
    }

    @PatchMapping("/updateById")
    public ResponseEntity<ItemResponseDto> updateById(@RequestParam("id") Long id, @RequestBody @Valid ItemRequestDto itemRequestDto) {
        return ResponseEntity.ok(itemService.updateItemById(id, itemRequestDto));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<ItemResponseDto> deleteById(@RequestParam @NotNull Long id) {
            return ResponseEntity.ok(itemService.deleteItemById(id));
    }




        //todo написать инструкцию DOCKER



}
