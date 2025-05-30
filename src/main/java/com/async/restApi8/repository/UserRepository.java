package com.async.restApi8.repository;

import com.async.restApi8.entity.User;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);      //для Security
    public Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:query) OR LOWER(u.email) = LOWER(:query)")
    public User findByUsernameOrEmail(@Param("query") String query);

    /*
    @Query(value = "DELETE FROM User u WHERE id = ?1 RETURNING *", nativeQuery = true)
    Optional<User> deleteAndReturnById(Long id);        почему-то не работает RETURNING
    */
}

