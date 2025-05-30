package com.async.restApi8.repository;

import com.async.restApi8.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByTitle(String name);
    //TODO сделать существование по содержанию, что-то типа совпадение на 70%

    Optional<Item> findByTitle(String title);
    Optional<Item> findById(Long id);
}
