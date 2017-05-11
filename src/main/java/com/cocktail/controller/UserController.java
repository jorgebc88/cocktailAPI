package com.cocktail.controller;

import com.cocktail.entity.User;
import com.cocktail.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger LOGGER = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        LOGGER.info("New User created.");
        boolean insertState = this.userService.insertUser(user);
        return insertState ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Set<User>> getUsers() {
        LOGGER.info("Get User.");
        Set<User> users = this.userService.getUsers();
        if (users.size() != 0) {
            return new ResponseEntity<>(users, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/{drinkId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String userName) {
        LOGGER.info("Get user by name.");
        return new ResponseEntity<>(this.userService.getUserByUserName(userName), HttpStatus.OK);
    }
}
