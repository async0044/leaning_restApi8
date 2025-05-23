package com.async.restApi8.service;

import com.async.restApi8.dto.ItemDto;
import com.async.restApi8.entity.Item;
import com.async.restApi8.mapper.ItemMapper;
import com.async.restApi8.repository.ItemRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDto addItem(Item item) {
        try {
            return ItemMapper.itemToDto(itemRepository.save(item));
        } catch (Exception e) {
            throw new ServiceException("Failed to add item" , e);
        }
    }

    public ItemDto getItemById(Long id) {
        try {
            return ItemMapper.itemToDto(itemRepository.findById(id).get());
        }
        catch (Exception e) {
            throw new ServiceException("Failed to get item" , e);
        }
    }

    public List<ItemDto> getAllItems() {
        List<ItemDto> items = new ArrayList<>();
        itemRepository.findAll().forEach(item -> items.add(ItemMapper.itemToDto(item)));
        return items;
    }


    public ItemDto updateItem(Item item) {
        try {
            return ItemMapper.itemToDto(itemRepository.save(item));
        }
        catch (Exception e) {
            throw new ServiceException("Failed to update item" , e);
        }
    }

    public ItemDto deleteItem(Long id) {
        try {
            ItemDto itemDto = ItemMapper.itemToDto(itemRepository.findById(id).get());
            itemRepository.deleteById(id);
            return itemDto;
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
