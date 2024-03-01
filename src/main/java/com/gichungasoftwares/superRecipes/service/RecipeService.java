package com.gichungasoftwares.superRecipes.service;

import com.gichungasoftwares.superRecipes.model.Recipe;
import com.gichungasoftwares.superRecipes.model.User;

import java.util.List;

public interface RecipeService {
    public Recipe createRecipe(Recipe recipe, User user);
    public Recipe findRecipeById(Long id) throws Exception;
    public void deleteRecipe(Long id) throws Exception;
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception;
    public List<Recipe> findAllRecipe();
    public Recipe likeRecipe(Long recipeId, User user) throws Exception;
}
