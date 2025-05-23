package com.async.restApi8.util;

import com.async.restApi8.entity.User;
import com.async.restApi8.repository.UserRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostConstructClass {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void addSomeUsers() {
        Faker faker = new Faker();
        List<User> users = userRepository.findAll();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);

        if (users.size() < 10) {
            for (int i = 0; i != 10; i++) {
                User user = new User();
                user.setUsername(faker.name().firstName() + " " + faker.name().lastName());
                user.setPassword(encoder.encode("12345"));
                user.setEmail(faker.internet().emailAddress());
                user.setRole("ROLE_USER");
                userRepository.save(user);
            }
        }

        if (users.stream().filter(user -> user.getRole().equals("ROLE_ADMIN")).count() == 0) {
            User user = new User();
            user.setUsername("async");
            user.setPassword(encoder.encode("12345"));
            user.setEmail("async0044@gmail.com");
            user.setRole("ROLE_ADMIN");
            userRepository.save(user);
        }
    }
}
