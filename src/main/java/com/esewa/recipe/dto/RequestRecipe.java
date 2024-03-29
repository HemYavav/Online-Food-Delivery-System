package com.esewa.recipe.dto;

import com.esewa.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestRecipe {
    private String title;
    private String image;
    private String description;
    private boolean vegetarian;
    private User user;
    private List<Integer> likes;
}
