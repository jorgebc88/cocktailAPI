package com.cocktail.controller;

import com.cocktail.DTO.CocktailDTO;
import com.cocktail.service.CocktailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {

    @Autowired
    private CocktailService cocktailService;

    private final Logger LOGGER = Logger.getLogger(CocktailController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<CocktailDTO> createCocktail(@RequestBody CocktailDTO cocktailDTO) {
        LOGGER.info("New cocktail created.");
        boolean insertState = this.cocktailService.insertCocktail(cocktailDTO);
        return insertState ? new ResponseEntity<>(cocktailDTO, HttpStatus.OK) :
                new ResponseEntity<>(cocktailDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Set<CocktailDTO>> getCocktails() {
        LOGGER.info("Get cocktails.");
        Set<CocktailDTO> cocktails = this.cocktailService.getCocktails();
        return new ResponseEntity<>(cocktails, HttpStatus.OK);
    }
}
