package com.cocktail.controller;

import com.cocktail.DTO.DrinkDTO;
import com.cocktail.service.DrinkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/drinks")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    private final Logger LOGGER = Logger.getLogger(DrinkController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<DrinkDTO> createDrink(@RequestBody DrinkDTO drinkDTO) {
        LOGGER.info("New Drink created.");
        boolean insertState = this.drinkService.insertDrink(drinkDTO);
        return insertState ? new ResponseEntity<>(drinkDTO, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Set<DrinkDTO>> getDrinks() {
        LOGGER.info("Get drinks.");
        Set<DrinkDTO> drinks = this.drinkService.getDrinks();
        if (drinks.size() != 0) {
            return new ResponseEntity<>(drinks, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

    }

    @RequestMapping(value = "/{drinkId}", method = RequestMethod.GET)
    public ResponseEntity<DrinkDTO> getDrinkById(@PathVariable("drinkId") Long drinkId) {
        LOGGER.info("Get drink by id.");
        return new ResponseEntity<>(this.drinkService.getDrinkById(drinkId), HttpStatus.OK);
    }
}
