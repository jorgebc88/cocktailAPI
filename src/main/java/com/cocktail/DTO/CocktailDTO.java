package com.cocktail.DTO;

import java.util.Set;

public class CocktailDTO {

    private long cocktailId;

    private String cocktailName;

    private Set<IngredientDTO> ingredients;

    private String cocktailType;

    private String glassType;

    public long getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(long cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getCocktailName() {
        return this.cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public Set<IngredientDTO> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCocktailType() {
        return this.cocktailType;
    }

    public void setCocktailType(String cocktailType) {
        this.cocktailType = cocktailType;
    }

    public String getGlassType() {
        return this.glassType;
    }

    public void setGlassType(String glassType) {
        this.glassType = glassType;
    }
}
