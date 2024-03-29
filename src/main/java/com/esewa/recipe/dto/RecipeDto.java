package com.esewa.recipe.dto;

import com.esewa.user.entity.User;

import java.util.List;

public class RecipeDto {
    private String title;
    private String image;
    private String description;
    private boolean vegetarian;
    private ConvertUserDtoForRecipe user;
    private List<Integer> likes;
}
