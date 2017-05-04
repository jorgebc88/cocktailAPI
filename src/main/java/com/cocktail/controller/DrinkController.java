package com.cocktail.controller;

import com.cocktail.DTO.DrinkDTO;
import com.cocktail.service.DrinkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@RestController
@RequestMapping("/drinks")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    private final Logger LOGGER = Logger.getLogger(CocktailController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public DrinkDTO createDrink (HttpServletResponse httpServletResponse, @RequestBody DrinkDTO drinkDTO){
        LOGGER.info("New Drink created.");
        boolean insertState = this.drinkService.insertDrink(drinkDTO);
        return insertState ? drinkDTO : null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Set<DrinkDTO> getDrinks (HttpServletResponse httpServletResponse){
        LOGGER.info("Get drinks.");
        Set<DrinkDTO> drinkDTOs = this.drinkService.getDrinks();
        return drinkDTOs;
    }

    @RequestMapping(value = "/{drinkId}", method = RequestMethod.GET)
    public DrinkDTO getDrinkById (HttpServletResponse httpServletResponse, @PathVariable("drinkId") Long drinkId){
        LOGGER.info("Get drink by id.");
        DrinkDTO drinkDTO = this.drinkService.getDrinkById(drinkId);
        return drinkDTO;
    }

}
