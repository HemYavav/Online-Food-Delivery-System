package com.esewa.mapper;

import com.esewa.recipe.dto.ConvertUserDtoForRecipe;
import com.esewa.recipe.dto.RecipeDto;
import com.esewa.recipe.dto.ResponseToRecipeDto;
import com.esewa.recipe.entity.Recipe;
import com.esewa.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeMappingService {
    private final ModelMapper modelMapper;

    public Recipe mappedRecipeDtoToRecipe(RecipeDto requestToRecipeDto) {
        log.warn("Assigning RecipeDto entity info to Recipe type");
        return modelMapper.map(requestToRecipeDto, Recipe.class);
    }

    public ResponseToRecipeDto mappedRecipeToRecipeDto(Recipe user) {
        log.warn("Assigning Recipe entity info to RecipeDto types");
        return modelMapper.map(user, ResponseToRecipeDto.class);
    }

    public List<ResponseToRecipeDto> mappedToRecipeDtoList(List<Recipe> recipes) {
        log.debug("Converting list of Recipe entities to RecipeDto list.");
        return recipes.stream()
                .sorted(Comparator.comparing(Recipe::getCreatedDate).reversed())
                .map(this::mappedRecipeToRecipeDto).collect(Collectors.toList());
    }

    public ConvertUserDtoForRecipe convertToUserDtoForRecipe(User user) {
        log.debug("Converting User to UserDto for entering User reference for recipe: "+ user);
        return modelMapper.map(user, ConvertUserDtoForRecipe.class);
    }


}
