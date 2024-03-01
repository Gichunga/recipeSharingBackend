package com.gichungasoftwares.superRecipes.repository;

import com.gichungasoftwares.superRecipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
