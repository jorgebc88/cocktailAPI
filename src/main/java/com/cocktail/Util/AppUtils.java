package com.cocktail.Util;

import com.cocktail.DTO.CocktailDTO;
import com.cocktail.DTO.DrinkDTO;
import com.cocktail.DTO.IngredientDTO;
import com.cocktail.entity.Cocktail;
import com.cocktail.entity.Drink;
import com.cocktail.entity.Ingredient;
import com.cocktail.repository.CocktailRepository;
import com.cocktail.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AppUtils {

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private CocktailRepository cocktailRepository;

    //Cocktail
    public Cocktail convertCocktailDTO(CocktailDTO cocktailDTO) {
        Cocktail cocktail = new Cocktail();
        cocktail.setCocktailName(cocktailDTO.getCocktailName());
        cocktail.setCocktailType(cocktailDTO.getCocktailType());
        cocktail.setGlassType(cocktailDTO.getGlassType());
        Set<Ingredient> ingredients = new HashSet<>();
        for(IngredientDTO ingredientDTO : cocktailDTO.getIngredients()){
            Ingredient ingredient = new Ingredient();
            ingredient.setQuantity(ingredientDTO.getQuantity());
            ingredient.setCocktail(cocktail);
            Drink drink = drinkRepository.findOne(ingredientDTO.getDrinkId());
            drink.setIngredients(null);
            ingredient.setDrink(drink);
            ingredients.add(ingredient);
        }
        cocktail.setIngredients(ingredients);
        return cocktail;
    }

    public Set<CocktailDTO> convertToCocktailsDTO(Set<Cocktail> cocktails) {
        Set<CocktailDTO> cocktailDTOS = new HashSet<>();
        for(Cocktail cocktail : cocktails){
            CocktailDTO cocktailDTO = this.convertToCocktailDTO(cocktail);
            cocktailDTOS.add(cocktailDTO);
        }
        return cocktailDTOS;
    }

    private CocktailDTO convertToCocktailDTO(Cocktail cocktail){
        CocktailDTO cocktailDTO = new CocktailDTO();
        cocktailDTO.setCocktailId(cocktail.getCocktailId());
        cocktailDTO.setGlassType(cocktail.getGlassType());
        cocktailDTO.setCocktailType(cocktail.getCocktailType());
        cocktailDTO.setCocktailId(cocktail.getCocktailId());
        cocktailDTO.setCocktailName(cocktail.getCocktailName());
        cocktailDTO.setIngredients(this.convertToIngredientDTO(cocktail.getIngredients()));
        return cocktailDTO;
    }
    // Drink part
    public Drink convertDrinkDTO(DrinkDTO drinkDTO) {
        Drink drink = new Drink();

        drink.setBottleMeasure(drinkDTO.getBottleMeasure());
        drink.setDrinkBrand(drinkDTO.getDrinkBrand());
        drink.setDrinkName(drinkDTO.getDrinkName());
        drink.setQuantityBottle(drinkDTO.getQuantityBottle());

        Set<Ingredient> ingredients = new HashSet<>();
        if(drinkDTO.getIngredients() != null) {
            for (IngredientDTO ingredientDTO : drinkDTO.getIngredients()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setQuantity(ingredientDTO.getQuantity());
                ingredient.setDrink(drink);
                Cocktail cocktail = cocktailRepository.findByCocktailName(ingredientDTO.getCocktailName());
                cocktail.setIngredients(null);
                ingredient.setCocktail(cocktail);
                ingredients.add(ingredient);
            }
            drink.setIngredients(ingredients);
        }
        return drink;

    }

    public Set<DrinkDTO> ConvertToDrinksDTO(Set<Drink> drinks){
        Set<DrinkDTO> drinkDTOs = new HashSet<>();
        for(Drink drink: drinks){
            DrinkDTO drinkDTO = this.convertToDrinkDTO(drink);
            drinkDTOs.add(drinkDTO);
        }
        return drinkDTOs;
    }

    public DrinkDTO convertToDrinkDTO(Drink drink){
        DrinkDTO drinkDTO = new DrinkDTO();
        drinkDTO.setDrinkId(drink.getDrinkId());
        drinkDTO.setQuantityBottle(drink.getQuantityBottle());
        drinkDTO.setDrinkName(drink.getDrinkName());
        drinkDTO.setBottleMeasure(drink.getBottleMeasure());
        drinkDTO.setDrinkBrand(drink.getDrinkBrand());
        drinkDTO.setIngredients(this.convertToIngredientDTO(drink.getIngredients()));
        return drinkDTO;
    }

    //Ingredient
    private Set<IngredientDTO> convertToIngredientDTO(Set<Ingredient> ingredients){
        Set<IngredientDTO> ingredientDTOS = new HashSet<>();
        for(Ingredient ingredient : ingredients) {
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setCocktailName(ingredient.getCocktail().getCocktailName());
            ingredientDTO.setDrinkId(ingredient.getDrink().getDrinkId());
            ingredientDTO.setDrinkName(ingredient.getDrink().getDrinkName());
            ingredientDTO.setQuantity(ingredient.getQuantity());
            ingredientDTO.setMeasure(ingredient.getDrink().getBottleMeasure());
            ingredientDTOS.add(ingredientDTO);
        }
        return ingredientDTOS;
    }
}
