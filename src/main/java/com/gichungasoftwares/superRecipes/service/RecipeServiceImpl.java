package com.gichungasoftwares.superRecipes.service;

import com.gichungasoftwares.superRecipes.model.Recipe;
import com.gichungasoftwares.superRecipes.model.User;
import com.gichungasoftwares.superRecipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe createdRecipe = new Recipe();
        createdRecipe.setTitle(recipe.getTitle());
        createdRecipe.setDescription(recipe.getDescription());
        createdRecipe.setImage(recipe.getImage());
        createdRecipe.setVegetarian(recipe.isVegetarian());
        createdRecipe.setUser(user);
        createdRecipe.setCreatedAt(LocalDateTime.now());

        return recipeRepository.save(createdRecipe);
    }

    @Override
    public Recipe findRecipeById(Long id) throws Exception {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()){
            return optionalRecipe.get();
        }
        throw new Exception("Recipe with id " + id + " was not found");
    }

    @Override
    public void deleteRecipe(Long id) throws Exception {
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Recipe oldRecipe = findRecipeById(id);
        if (recipe.getTitle() != null){
            oldRecipe.setTitle(recipe.getTitle());
        }
        if (recipe.getDescription() != null){
            oldRecipe.setDescription(recipe.getDescription());
        }
        if (recipe.getImage() != null){
            oldRecipe.setImage(recipe.getImage());
        }
        oldRecipe.setVegetarian(recipe.isVegetarian());
        return recipeRepository.save(oldRecipe);
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe toggleLikeRecipe(Long recipeId, User user) {
        try {
            Recipe recipe = findRecipeById(recipeId);

            if (recipe.getLikes().contains(user.getId())) {
                recipe.getLikes().remove(user.getId());
            } else {
                recipe.getLikes().add(user.getId());
            }

            return recipeRepository.save(recipe);
        } catch (Exception e) {
            // Log the exception or handle it accordingly
            e.printStackTrace();
            throw new RuntimeException("An error occurred while toggling like for recipe with ID: " + recipeId);
        }
    }

}
