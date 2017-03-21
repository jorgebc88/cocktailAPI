package com.cocktail.repository;

import com.cocktail.entity.Cocktail;
import org.springframework.data.repository.CrudRepository;

public interface CocktailRepository extends CrudRepository<Cocktail, Long> {

}
