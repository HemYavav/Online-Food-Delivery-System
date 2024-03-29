package com.esewa.recipe.dto.mapper;

import com.esewa.recipe.dto.ConvertUserDtoForRecipe;
import com.esewa.recipe.dto.RecipeDto;
import com.esewa.recipe.entity.Recipe;
import com.esewa.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecipeModelMapper {
 private final ModelMapper productModelMapper;

    public RecipeModelMapper(ModelMapper modelMapper) {
        this.productModelMapper = modelMapper;
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    public Recipe convertToEntity(RecipeDto productDto) {
        log.debug("Converting ProductDto to Product: {}", productDto);
        return productModelMapper.map(productDto, Recipe.class);
    }
    public RecipeDto convertToDto(Recipe product) {
        log.debug("Converting Product to ProductDto: {}", product);
        return productModelMapper.map(product, RecipeDto.class);
    }

    public ConvertUserDtoForRecipe convertToUserDtoForRecipe(User user) {
        log.debug("Converting User to UserDto for entering User reference for recipe", user);
        return productModelMapper.map(user, ConvertUserDtoForRecipe.class);
    }




}
