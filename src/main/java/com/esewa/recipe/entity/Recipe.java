package com.esewa.recipe.entity;

import com.esewa.shared.abstractcollection.AbstractEntity;
import com.esewa.user.entity.User;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "recipe_details")
public class Recipe extends AbstractEntity {
    private String title;
    private String image;
    private String description;
    private boolean vegetarian;
    @ManyToOne
    private User user;
    @ElementCollection
    private List<Integer> likes = new ArrayList<>();


}
