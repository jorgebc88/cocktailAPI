package com.cocktail.service;

import com.cocktail.DTO.DrinkDTO;
import com.cocktail.Util.CocktailUtils;
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

    @Autowired
    private CocktailUtils cocktailUtils;

    public boolean insertDrink(DrinkDTO drinkDTO) {
        try {
            Drink drink = cocktailUtils.convertDrinkDTO(drinkDTO);
            this.drinkRepository.save(drink);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Set<DrinkDTO> getDrinks() {
        Iterable<Drink> drinkIterable = this.drinkRepository.findAll();
        Set<Drink> drinks = new HashSet<>();
        drinkIterable.iterator().forEachRemaining(drinks::add);
        Set<DrinkDTO> drinkDTOs = cocktailUtils.ConvertToDrinksDTO(drinks);
        return drinkDTOs;
    }

}
