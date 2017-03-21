package com.cocktail.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cocktail")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cocktail_id", unique = true, nullable = false)
    private long cocktailId;

    private String cocktailName;

    @OneToMany(mappedBy = "cocktail", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ingredient> ingredients;

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

    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
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
