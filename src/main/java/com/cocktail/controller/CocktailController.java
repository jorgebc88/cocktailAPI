package com.cocktail.controller;

import com.cocktail.entity.Cocktail;
import com.cocktail.entity.Ingredient;
import com.cocktail.service.CocktailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@RestController
@RequestMapping("/cocktail")
public class CocktailController {

    @Autowired
    private CocktailService cocktailService;

    private final Logger LOGGER = Logger.getLogger(CocktailController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Cocktail createCocktail (HttpServletResponse httpServletResponse, @RequestBody Cocktail cocktail){
        LOGGER.info("New cocktail created.");
        for(Ingredient ingredient : cocktail.getIngredients()){
            ingredient.setCocktail(cocktail);
        }
        boolean insertState = this.cocktailService.insertCocktail(cocktail);
        return insertState ? cocktail : null;
    }

    @RequestMapping(value = "/cocktails", method = RequestMethod.GET)
    public Set<Cocktail> getCocktails (HttpServletResponse httpServletResponse){
        LOGGER.info("Get cocktails.");
        Set<Cocktail> cocktails = this.cocktailService.getCocktails();
        return cocktails;
    }

}
