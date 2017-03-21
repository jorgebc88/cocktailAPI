package com.cocktail.controller;

import com.cocktail.DTO.CocktailDTO;
import com.cocktail.Util.CocktailUtils;
import com.cocktail.entity.Cocktail;
import com.cocktail.entity.Ingredient;
import com.cocktail.service.CocktailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {

    @Autowired
    private CocktailService cocktailService;

    private final Logger LOGGER = Logger.getLogger(CocktailController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public CocktailDTO createCocktail (HttpServletResponse httpServletResponse, @RequestBody CocktailDTO cocktailDTO){
        LOGGER.info("New cocktail created.");
        boolean insertState = this.cocktailService.insertCocktail(cocktailDTO);
        return insertState ? cocktailDTO : null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Set<CocktailDTO>> getCocktails (HttpServletResponse httpServletResponse){
        LOGGER.info("Get cocktails.");
        Set<CocktailDTO> cocktails = this.cocktailService.getCocktails();
//        return cocktails;
        return new ResponseEntity<Set<CocktailDTO>>(cocktails, HttpStatus.OK);
    }

}
