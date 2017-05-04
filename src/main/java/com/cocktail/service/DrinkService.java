package com.cocktail.service;

import com.cocktail.DTO.DrinkDTO;
import com.cocktail.Util.AppUtils;
import com.cocktail.entity.Drink;
import com.cocktail.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private AppUtils appUtils;

    public boolean insertDrink(DrinkDTO drinkDTO) {
        try {
            Drink drink = appUtils.convertDrinkDTO(drinkDTO);
            this.drinkRepository.save(drink);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<DrinkDTO> getDrinks() {
        Iterable<Drink> drinkIterable = this.drinkRepository.findAll();
        Set<Drink> drinks = new HashSet<>();
        drinkIterable.iterator().forEachRemaining(drinks::add);
        return appUtils.ConvertToDrinksDTO(drinks);
    }

    public DrinkDTO getDrinkById(Long drinkId) {
        Drink drink = this.drinkRepository.findOne(drinkId);
        return appUtils.convertToDrinkDTO(drink);
    }
}
