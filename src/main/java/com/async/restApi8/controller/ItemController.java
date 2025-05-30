package com.async.restApi8.controller;

import com.async.restApi8.dto.ItemRequestDto;
import com.async.restApi8.dto.ItemResponseDto;
import com.async.restApi8.entity.Item;
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


    @PostMapping("/addItem")
    public ResponseEntity<ItemResponseDto> addItem(@RequestBody ItemRequestDto itemRequestDto) {
        return ResponseEntity.ok().body(itemService.addItem(itemRequestDto));
    }

    @GetMapping("/getItemById/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        try {
            ItemResponseDto dto = itemService.getItemById(id);              //сделать конкретные ответы по ошибкам catch (NotFounExeption)... catch (AccessDeniedException)...
            return ResponseEntity.ok(dto);
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    @GetMapping("/getAllItems")
    public ResponseEntity<List<ItemResponseDto>> getAllItems() {
        try {
            return ResponseEntity.ok(itemService.getAllItems());
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/updateById")
    public ResponseEntity<ItemResponseDto> updateById(@RequestBody Item item) {
        try {
            return ResponseEntity.ok(itemService.updateItem(item));
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ItemResponseDto> deleteById(@PathVariable Long id) {
        try {
            ItemResponseDto itemResponseDto = itemService.deleteItem(id);
            return ResponseEntity.ok(itemResponseDto);
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




        //todo написать инструкцию DOCKER



}
