package com.cocktail.DTO;

import java.util.Set;

public class DrinkDTO {
    private long drinkId;

    private String drinkName;

    private String drinkBrand;

    private long quantityBottle;

    private String bottleMeasure;

    private Set<IngredientDTO> ingredients;

    public long getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(long drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public long getQuantityBottle() {
        return quantityBottle;
    }

    public void setQuantityBottle(long quantityBottle) {
        this.quantityBottle = quantityBottle;
    }

    public Set<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public String getBottleMeasure() {
        return bottleMeasure;
    }

    public void setBottleMeasure(String bottleMeasure) {
        this.bottleMeasure = bottleMeasure;
    }

    public String getDrinkBrand() {
        return drinkBrand;
    }

    public void setDrinkBrand(String drinkBrand) {
        this.drinkBrand = drinkBrand;
    }

}
