package com.cocktail.repository;

import com.cocktail.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUserName(String username);

}
