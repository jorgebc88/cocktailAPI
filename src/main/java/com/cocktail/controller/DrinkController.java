package com.cocktail.controller;

import com.cocktail.DTO.DrinkDTO;
import com.cocktail.service.DrinkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@RestController
@RequestMapping("/drinks")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    private final Logger LOGGER = Logger.getLogger(DrinkController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public DrinkDTO createDrink(@RequestBody DrinkDTO drinkDTO) {
        LOGGER.info("New Drink created.");
        boolean insertState = this.drinkService.insertDrink(drinkDTO);
        return insertState ? drinkDTO : null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Set<DrinkDTO> getDrinks() {
        LOGGER.info("Get drinks.");
        return this.drinkService.getDrinks();
    }

    @RequestMapping(value = "/{drinkId}", method = RequestMethod.GET)
    public DrinkDTO getDrinkById(@PathVariable("drinkId") Long drinkId) {
        LOGGER.info("Get drink by id.");
        return this.drinkService.getDrinkById(drinkId);
    }
}
