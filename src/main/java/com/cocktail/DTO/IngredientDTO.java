package com.cocktail.DTO;

public class IngredientDTO {
    private Long cocktailId;

    private Long drinkId;

    private long quantity;

    public Long getCocktailId() {
        return this.cocktailId;
    }

    public void setCocktailId(Long cocktailId) {
        this.cocktailId = cocktailId;
    }

    public Long getDrinkId() {
        return this.drinkId;
    }

    public void setDrinkId(Long drinkId) {
        this.drinkId = drinkId;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

}
