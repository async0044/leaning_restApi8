package com.async.restApi8.service;

import com.async.restApi8.dto.ItemRequestDto;
import com.async.restApi8.dto.ItemResponseDto;
import com.async.restApi8.entity.Item;
import com.async.restApi8.mapper.ItemMapper;
import com.async.restApi8.repository.ItemRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public ItemResponseDto addItem(ItemRequestDto itemRequestDto) {
        if (itemRepository.existsByTitle(itemRequestDto.title())) {
            throw new ServiceException("This title already exists");
        }
            Item item = new Item();
            item.setTitle(itemRequestDto.title());
            item.setContent(itemRequestDto.content());
            item.setAuthor(itemRequestDto.author());
            return ItemMapper.itemToDto(itemRepository.save(item));
    }

    public ItemResponseDto getItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ServiceException("This ID not found"));
        return ItemMapper.itemToDto(item);
    }

    public ItemResponseDto getItemByTitle(String title) {
        Item item = itemRepository.findByTitle(title).orElseThrow(() -> new ServiceException("This title not found"));
        return ItemMapper.itemToDto(item);
    }

    public List<ItemResponseDto> getAllItems() {
        try {
            List<ItemResponseDto> itemDtoList = itemRepository.findAll().stream().map(item -> ItemMapper.itemToDto(item)).collect(Collectors.toList());
            return itemDtoList;
        } catch (Exception e) {
            throw new ServiceException("Error getting item list");
        }
    }


    public ItemResponseDto updateItemById(Long id, ItemRequestDto itemRequestDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ServiceException("This ID not found"));
        item.setTitle(itemRequestDto.title());
        item.setContent(itemRequestDto.content());
        item.setAuthor(itemRequestDto.author());
        return ItemMapper.itemToDto(itemRepository.save(item));
    }

    public ItemResponseDto deleteItem(Long id) {
        try {
            ItemResponseDto itemResponseDto = ItemMapper.itemToDto(itemRepository.findById(id).get());
            itemRepository.deleteById(id);
            return itemResponseDto;
        }
        catch (Exception e) {
            throw new ServiceException("Failed to delete item" , e);
        }
    }











/*
    @PostConstruct
    public void fillDB() {
        Faker faker = new Faker();
        List<Item> items = new ArrayList<>();

            for (int i = 0; i != 50; i++) {
                Item item = new Item();
                item.setTitle(faker.book().title());
                item.setAuthor(faker.book().author());
                item.setContent(faker.lorem().sentence());
                items.add(item);
            }

            items.stream().forEach(item -> {
                try {
                    addItem(item);
                }
                catch (Exception e) {}
            });
    }
 */
}
