package com.esewa.recipe.service;

import com.esewa.recipe.dto.RecipeDto;
import com.esewa.recipe.dto.ResponseToRecipeDto;
import com.esewa.recipe.entity.Recipe;
import com.esewa.shared.exception.exceptionhandler.RecipeAlreadyFoundByUserEmail;
import com.esewa.shared.exception.exceptionhandler.RecipeNotExistException;
import com.esewa.shared.exception.exceptionhandler.RecipeNotFoundException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserAlreadyDeletedException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserNotFoundException;
import com.esewa.user.entity.User;

import java.util.List;

public interface RecipeService {
    public ResponseToRecipeDto createRecipe(RecipeDto recipeDto, int userId) throws RecipeAlreadyFoundByUserEmail, RecipeNotFoundException, UserNotFoundException, UserAlreadyDeletedException;
    public Recipe updateRecipeBuUserId(Recipe recipe, User user) throws  RecipeNotFoundException;

    public Recipe findRecipeById(int id) throws RecipeNotExistException;
public String deleteRecipe(int id) throws RecipeNotFoundException;
public Recipe updateRecipe(Recipe recipe,  int id) throws RecipeNotFoundException;
public List<Recipe> findAllRecipes() throws RecipeNotExistException;
public Recipe likeRecipe(int recipeId, User user) throws RecipeNotExistException;

}

