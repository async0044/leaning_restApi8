package com.async.restApi8.repository;

import com.async.restApi8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:query) OR LOWER(u.email) = LOWER(:query)")
    public User findByUsernameOrEmailCaseInsensitive(@Param("query") String query);
}

