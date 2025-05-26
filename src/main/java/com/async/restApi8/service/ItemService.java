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

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public ItemResponseDto addItem(ItemRequestDto itemRequestDto) {
        try {
            Item item = new Item();
            item.setTitle(itemRequestDto.title());
            item.setContent(itemRequestDto.content());
            item.setAuthor(itemRequestDto.author());
            return ItemMapper.itemToDto(itemRepository.save(item));
        } catch (Exception e) {
            throw new ServiceException("Failed to add item" , e);
        }
    }

    public ItemResponseDto getItemById(Long id) {
        try {
            return ItemMapper.itemToDto(itemRepository.findById(id).get());
        }
        catch (Exception e) {
            throw new ServiceException("Failed to get item" , e);
        }
    }

    public List<ItemResponseDto> getAllItems() {
        List<ItemResponseDto> items = new ArrayList<>();
        itemRepository.findAll().forEach(item -> items.add(ItemMapper.itemToDto(item)));
        return items;
    }


    public ItemResponseDto updateItem(Item item) {
        try {
            return ItemMapper.itemToDto(itemRepository.save(item));
        }
        catch (Exception e) {
            throw new ServiceException("Failed to update item" , e);
        }
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
