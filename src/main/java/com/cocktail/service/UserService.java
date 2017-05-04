package com.cocktail.service;

import com.cocktail.Util.AppUtils;
import com.cocktail.entity.User;
import com.cocktail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppUtils appUtils;

    public boolean insertUser(User user) {
        try {
//            Drink drink = appUtils.convertDrinkDTO(drinkDTO);
            this.userRepository.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<User> getUsers() {
        Iterable<User> userIterable = this.userRepository.findAll();
        Set<User> users = new HashSet<>();
        userIterable.iterator().forEachRemaining(users::add);
//        Set<DrinkDTO> drinkDTOs = appUtils.ConvertToDrinksDTO(drinks);
        return users;
    }

    public User getUserByUserName(String userName) {
        User user = this.userRepository.findByUserName(userName);
        return user;
    }
}
