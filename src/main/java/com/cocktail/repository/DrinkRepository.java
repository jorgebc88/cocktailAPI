package com.cocktail.repository;

import com.cocktail.entity.Drink;
import org.springframework.data.repository.CrudRepository;

public interface DrinkRepository extends CrudRepository<Drink, Long> {

    public Drink findByDrinkName(String drinkName);

}
