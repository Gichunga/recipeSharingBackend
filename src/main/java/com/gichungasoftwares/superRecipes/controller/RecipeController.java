package com.gichungasoftwares.superRecipes.controller;

import com.gichungasoftwares.superRecipes.model.Recipe;
import com.gichungasoftwares.superRecipes.model.User;
import com.gichungasoftwares.superRecipes.service.RecipeService;
import com.gichungasoftwares.superRecipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception {
        User user = userService.findUserById(userId);
        return recipeService.createRecipe(recipe, user);
    }

    @GetMapping
    public List<Recipe> findAllRecipe() {
        return recipeService.findAllRecipe();
    }

    @GetMapping("/{recipeId}")
    public Recipe findRecipeById(@PathVariable Long recipeId) throws Exception{
        return recipeService.findRecipeById(recipeId);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) throws Exception{
        recipeService.deleteRecipe(id);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {
        return recipeService.updateRecipe(recipe, id);
    }

    @PutMapping("/{recipeId}/like/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long recipeId, @PathVariable Long userId) throws Exception {
        User user = userService.findUserById(userId);
        return recipeService.toggleLikeRecipe(recipeId, user);
    }

}
