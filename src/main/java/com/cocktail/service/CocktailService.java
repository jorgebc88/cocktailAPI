package com.cocktail.service;

import com.cocktail.DTO.CocktailDTO;
import com.cocktail.Util.CocktailUtils;
import com.cocktail.entity.Cocktail;
import com.cocktail.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CocktailService {

    @Autowired
    private CocktailRepository cocktailRepository;

    @Autowired
    private CocktailUtils cocktailUtils;

    public boolean insertCocktail(CocktailDTO cocktailDTO) {
        try {
            Cocktail cocktail = cocktailUtils.convertCocktailDTO(cocktailDTO);
            this.cocktailRepository.save(cocktail);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Set<CocktailDTO> getCocktails() {
        Iterable<Cocktail> CocktailsIterable = this.cocktailRepository.findAll();
        Set<Cocktail> cocktails = new HashSet<>();
        CocktailsIterable.iterator().forEachRemaining(cocktails::add);
        return cocktailUtils.ConvertToCocktailsDTO(cocktails);
    }


}
