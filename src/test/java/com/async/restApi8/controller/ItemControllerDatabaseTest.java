package com.async.restApi8.controller;

import com.async.restApi8.entity.Item;
import com.async.restApi8.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemControllerDatabaseTest {

    private static final Logger log = LoggerFactory.getLogger(ItemControllerDatabaseTest.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void addToDB_getFromDB() {

        Item testItem = new Item("TEST-TITLE", "TEST-CONTENT", "TEST-AUTHOR");
        Item savedItem = entityManager.persistAndFlush(testItem);
        log.info("Saved item: {}", savedItem);

        Optional<Item> foundedItem = itemRepository.findById(savedItem.getId());
        assertTrue(foundedItem.isPresent());
        log.info("Found item: {}", foundedItem.orElse(null));

        assertEquals(savedItem.getTitle(), foundedItem.get().getTitle());
        log.info("Equals title: {}", savedItem.getTitle().equals(foundedItem.get().getTitle()));

        assertEquals(savedItem.getContent(), foundedItem.get().getContent());
        log.info("Equals content: {}", savedItem.getContent().equals(foundedItem.get().getContent()));

        assertEquals(savedItem.getAuthor(), foundedItem.get().getAuthor());
        log.info("Equals author: {}", savedItem.getAuthor().equals(foundedItem.get().getAuthor()));
    }
}
