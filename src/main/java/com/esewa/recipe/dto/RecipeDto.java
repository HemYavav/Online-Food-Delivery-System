package com.esewa.recipe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class RecipeDto {
    private String title;
    private String image;
    private String description;
    private boolean vegetarian;
    private ConvertUserDtoForRecipe user;
    private List<Integer> likes;
}
