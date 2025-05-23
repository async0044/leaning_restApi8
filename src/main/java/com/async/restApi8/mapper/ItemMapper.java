package com.async.restApi8.mapper;

import com.async.restApi8.dto.ItemDto;
import com.async.restApi8.entity.Item;

public class ItemMapper {
    public static ItemDto itemToDto(Item item) {
        return new ItemDto(item.getId(), item.getTitle(), item.getContent(), item.getAuthor());
    }

    public static Item dtoToItem(ItemDto itemDto) {
        return null;          //НУЖНО ЛИ???
    }
}
