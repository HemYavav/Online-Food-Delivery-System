package com.esewa.recipe.repository;

import com.esewa.recipe.dto.ResponseToRecipeDto;
import com.esewa.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Optional<Recipe> findByTitleAndUserEmail(String title, String userEmail);
    Optional<Recipe> findRecipeById(int id);
    Optional<Recipe> findRecipeByTitle(String title);
}