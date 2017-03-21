package com.cocktail.service;

import com.cocktail.entity.Cocktail;
import com.cocktail.entity.Drink;
import com.cocktail.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    public boolean insertDrink(Drink drink) {
        try {
            this.drinkRepository.save(drink);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Set<Drink> getDrinks() {
        Iterable<Drink> drinkIterable = this.drinkRepository.findAll();
        Set<Drink> drinks = new HashSet<>();
        drinkIterable.iterator().forEachRemaining(drinks::add);
        return drinks;
    }

}
