package com.esewa.recipe.controller;

import com.esewa.recipe.dto.ConvertUserDtoForRecipe;
import com.esewa.recipe.entity.Recipe;
import com.esewa.recipe.service.RecipeService;
import com.esewa.shared.endpoint.EndpointConstants;
import com.esewa.shared.exception.exceptionhandler.RecipeAlreadyFoundByUserEmail;
import com.esewa.shared.exception.exceptionhandler.RecipeNotExistException;
import com.esewa.shared.exception.exceptionhandler.RecipeNotFoundException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserAlreadyDeletedException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserNotFoundException;
import com.esewa.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointConstants.API_RECIPE)
public class RecipeController {
    private final UserService userService;
    private final RecipeService recipeService;

    @PostMapping(EndpointConstants.API_CREATE_BY_USER_ID)
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable int userId) throws UserNotFoundException, RecipeAlreadyFoundByUserEmail, UserAlreadyDeletedException, RecipeNotFoundException {
        return recipeService.createRecipe(recipe, userService.findUserById(userId));
    }

    @PostMapping(EndpointConstants.API_UPDATED_BY_USER_ID)
    public Recipe updateRecipeByUserId(@RequestBody Recipe recipe, @PathVariable int userId) throws RecipeNotFoundException {
        return recipeService.updateRecipe(recipe, userId);
    }

    @GetMapping(EndpointConstants.GET_ALL)
    public List<Recipe> getAllRecipe() throws RecipeNotExistException {
        return recipeService.findAllRecipes();
    }

    @DeleteMapping(EndpointConstants.DELETE_BY_ID)
    public String deleteRecipeById(@PathVariable int id) throws RecipeNotFoundException {
        return recipeService.deleteRecipe(id);
    }

    @PostMapping(EndpointConstants.API_LIKE_RECIPE_BY_USER)
    public Recipe likesRecipe(@PathVariable int id, @PathVariable int userId) throws UserNotFoundException, UserAlreadyDeletedException, RecipeNotExistException {
        return recipeService.likeRecipe(id, userService.findUserById(userId));
    }
}
