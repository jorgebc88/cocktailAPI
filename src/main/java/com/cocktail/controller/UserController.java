package com.cocktail.controller;

import com.cocktail.entity.User;
import com.cocktail.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger LOGGER = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/init/", method = RequestMethod.GET)
    public User init (HttpServletResponse httpServletResponse){
        LOGGER.info("New User created.");
        User user = new User();
        user.setUserName("Jorge");
        user.setPassword("123");
        user.setRole("ADMIN");
        boolean insertState = this.userService.insertUser(user);
        return insertState ? user : null;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public User createUser (HttpServletResponse httpServletResponse, @RequestBody User user){
        LOGGER.info("New User created.");
        boolean insertState = this.userService.insertUser(user);
        return insertState ? user : null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Set<User> getUsers (HttpServletResponse httpServletResponse){
        LOGGER.info("Get Users.");
        Set<User> users = this.userService.getUsers();
        return users;
    }

    @RequestMapping(value = "/{drinkId}", method = RequestMethod.GET)
    public User getUserByUserName (HttpServletResponse httpServletResponse, @PathVariable("userName") String userName){
        LOGGER.info("Get user by name.");
        User user = this.userService.getUserByUserName(userName);
        return user;
    }

}
