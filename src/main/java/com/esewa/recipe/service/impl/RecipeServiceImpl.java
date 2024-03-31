package com.esewa.recipe.service.impl;

import com.esewa.mapper.RecipeMappingService;
import com.esewa.mapper.UserMappingService;
import com.esewa.recipe.dto.RecipeDto;
import com.esewa.recipe.dto.ResponseToRecipeDto;
import com.esewa.recipe.entity.Recipe;
import com.esewa.recipe.repository.RecipeRepository;
import com.esewa.recipe.service.RecipeService;
import com.esewa.shared.LastModifiedDate;
import com.esewa.shared.enumcollection.Status;
import com.esewa.shared.exception.exceptionhandler.RecipeAlreadyFoundByUserEmail;
import com.esewa.shared.exception.exceptionhandler.RecipeNotExistException;
import com.esewa.shared.exception.exceptionhandler.RecipeNotFoundException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserAlreadyDeletedException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserNotFoundException;
import com.esewa.shared.message.MessageConstants;
import com.esewa.user.dto.UserResponseDto;
import com.esewa.user.entity.User;
import com.esewa.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMappingService recipeMappingService;
    private final UserService userService;
    private final UserMappingService userMappingService;


    @Override
    public ResponseToRecipeDto createRecipe(RecipeDto recipeDto, int userId) throws RecipeAlreadyFoundByUserEmail, UserNotFoundException, UserAlreadyDeletedException {

User user = userService.findUserById(userId);
        Optional<Recipe> isRecipeExistedByUSerEmail = recipeRepository.findByTitleAndUserEmail(recipeDto.getTitle(), user.getEmail());
        if (isRecipeExistedByUSerEmail.isPresent()) {
            throw new RecipeAlreadyFoundByUserEmail();
        }

        Recipe createdRecipe = recipeMappingService.mappedRecipeDtoToRecipe(recipeDto);
        createdRecipe.setStatus(Status.ACTIVE);
        createdRecipe.setUser(user);
        return recipeMappingService.mappedRecipeToRecipeDto(recipeRepository.save(createdRecipe));
    }


    @Override
    public Recipe updateRecipeBuUserId(Recipe recipe, User user) throws  RecipeNotFoundException {
        Optional<Recipe> isRecipeExistedByUSerEmail = recipeRepository.findByTitleAndUserEmail(recipe.getTitle(), user.getEmail());
        if (isRecipeExistedByUSerEmail.isEmpty()) {
            throw new RecipeNotFoundException();
        }
        Recipe createdRecipe = new Recipe();
        createdRecipe.setTitle(recipe.getTitle());
        createdRecipe.setImage(recipe.getImage());
        createdRecipe.setUser(recipe.getUser());
        createdRecipe.setDescription(recipe.getDescription());
        return createdRecipe;
    }


    @Override
    public Recipe findRecipeById(int id) throws RecipeNotExistException {

        Optional<Recipe> isRecipeExisted = recipeRepository.findById(id);

        if (isRecipeExisted.isEmpty()) {
            throw new RecipeNotExistException();
        }
        return isRecipeExisted.get();
    }

    @Override
    public String deleteRecipe(int id) throws RecipeNotFoundException {
        Optional<Recipe> isRecipeExisted = recipeRepository.findRecipeById(id);
        if (isRecipeExisted.isEmpty()) {
            throw new RecipeNotFoundException();
        }
//        will be changed
        recipeRepository.deleteById(id);
        return MessageConstants.SUCCESSFULLY_DELETED;
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, int id) throws RecipeNotFoundException {
        Optional<Recipe> isRecipeExisted = recipeRepository.findRecipeById(id);
        Optional<Recipe> isRecipeExistedByTitle = recipeRepository.findRecipeByTitle(recipe.getTitle());

        if (isRecipeExisted.isEmpty() || isRecipeExistedByTitle.isEmpty()) {
            throw new RecipeNotFoundException();
        }
        if (recipe.getTitle() != null) {
            isRecipeExisted.get().setTitle(recipe.getTitle());
        }
        if (recipe.getImage() != null) {
            isRecipeExisted.get().setImage(recipe.getImage());
        }
        if (recipe.getDescription() != null) {
            isRecipeExisted.get().setDescription(recipe.getDescription());
        }
        isRecipeExisted.get().setLastModifiedDate(LastModifiedDate.getLastModifiedDate());
        Recipe recipe1 = isRecipeExisted.get();
        return recipeRepository.save(recipe1);
    }

    @Override
    public List<Recipe> findAllRecipes() throws RecipeNotExistException {
        List<Recipe> findAllRecipes = recipeRepository.findAll();
        if (findAllRecipes.isEmpty()) {
            throw new RecipeNotExistException();
        }
        return recipeRepository.findAll();
    }

    @Override
    public Recipe likeRecipe(int recipeId, User user) throws RecipeNotExistException {
        Recipe recipe = findRecipeById(recipeId);

        if (recipe.getLikes().contains(user.getId())) {
            recipe.getLikes().remove(user.getId());
        } else {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepository.save(recipe);
    }
}
