package com.cocktail.controller;

import com.cocktail.entity.Cocktail;
import com.cocktail.entity.Drink;
import com.cocktail.service.DrinkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@RestController
@RequestMapping("/drink")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    private final Logger LOGGER = Logger.getLogger(CocktailController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Drink createDrink (HttpServletResponse httpServletResponse, @RequestBody Drink drink){
        LOGGER.info("New Drink created.");
        boolean insertState = this.drinkService.insertDrink(drink);
        return insertState ? drink : null;
    }

    @RequestMapping(value = "/drinks", method = RequestMethod.GET)
    public Set<Drink> getDrinks (HttpServletResponse httpServletResponse){
        LOGGER.info("Get drinks.");
        Set<Drink> drinks = this.drinkService.getDrinks();
        return drinks;
    }

}
