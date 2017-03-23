package com.cocktail.DTO;

public class IngredientDTO {
    private String cocktailName;

    private String drinkName;

    private long quantity;

    private String measure;

    public String getCocktailName() {
        return this.cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public String getDrinkName() {
        return this.drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
