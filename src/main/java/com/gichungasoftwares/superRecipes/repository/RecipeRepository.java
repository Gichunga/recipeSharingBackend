package com.gichungasoftwares.superRecipes.repository;

import com.gichungasoftwares.superRecipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
