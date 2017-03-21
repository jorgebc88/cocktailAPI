package com.cocktail.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="drink")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_id", unique = true, nullable = false)
    private long drinkId;

    private String drinkName;

    private String drinkBrand;

    private long quantityBottle;

    private String bottleMeasure;

    @OneToMany(mappedBy = "drink")
    private Set<Ingredient> ingredients;

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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
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
