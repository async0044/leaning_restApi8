package com.async.restApi8.controller;

import com.async.restApi8.dto.ItemDto;
import com.async.restApi8.entity.Item;
import com.async.restApi8.mapper.ItemMapper;
import com.async.restApi8.service.ItemService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PutMapping("/addItem")
    public ResponseEntity<ItemDto> addItem(@RequestBody Item item) {
        try {
            return ResponseEntity.ok(itemService.addItem(item));
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
/*
    @GetMapping("/getItemById")
    public ItemDto getItemById(@RequestParam Long id) {
        return itemService.getItemById(id);
    }
*/
    @GetMapping("/getItemById/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        try {
            ItemDto dto = itemService.getItemById(id);              //сделать конкретные ответы по ошибкам catch (NotFounExeption)... catch (AccessDeniedException)...
            return ResponseEntity.ok(dto);
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    @GetMapping("/getAllItems")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        try {
            return ResponseEntity.ok(itemService.getAllItems());
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/updateById")
    public ResponseEntity<ItemDto> updateById(@RequestBody Item item) {
        try {
            return ResponseEntity.ok(itemService.updateItem(item));
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ItemDto> deleteById(@PathVariable Long id) {
        try {
            ItemDto itemDto = itemService.deleteItem(id);
            return ResponseEntity.ok(itemDto);
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




        //todo написать инструкцию DOCKER



}
