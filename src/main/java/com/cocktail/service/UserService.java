package com.cocktail.service;

import com.cocktail.entity.User;
import com.cocktail.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final Logger LOGGER = Logger.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public boolean insertUser(User user) {
        boolean inserted = true;
        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            LOGGER.error("There was an error trying to save a new User.", e);
            inserted = false;
        }
        return inserted;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<User> getUsers() {
        Iterable<User> userIterable = this.userRepository.findAll();
        Set<User> users = new HashSet<>();
        userIterable.iterator().forEachRemaining(users::add);
        return users;
    }

    public User getUserByUserName(String userName) {
        User user = this.userRepository.findByUserName(userName);
        return user;
    }
}
