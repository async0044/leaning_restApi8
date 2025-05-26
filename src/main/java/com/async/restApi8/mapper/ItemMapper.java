package com.async.restApi8.mapper;

import com.async.restApi8.dto.ItemResponseDto;
import com.async.restApi8.entity.Item;

public class ItemMapper {
    public static ItemResponseDto itemToDto(Item item) {
        return new ItemResponseDto(item.getId(), item.getTitle(), item.getContent(), item.getAuthor());
    }

    public static Item dtoToItem(ItemResponseDto itemResponseDto) {
        Item item = new Item();
        item.setTitle(itemResponseDto.title());
        item.setContent(itemResponseDto.content());     //сейчас всё делает сервис, подумать нужен ли вообще маппер для response
        item.setAuthor(itemResponseDto.author());
        return item;          //НУЖНО ЛИ???
    }
}
