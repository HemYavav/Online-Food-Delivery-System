package com.esewa.recipe.dto;

import com.esewa.shared.enumcollection.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConvertUserDtoForRecipe {
    private int id;
    private String username;
    private String email;
    private Status status;
}
