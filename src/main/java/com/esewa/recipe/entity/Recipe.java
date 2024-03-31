package com.esewa.recipe.entity;

import com.esewa.shared.abstractcollection.AbstractEntity;
import com.esewa.shared.enumcollection.Status;
import com.esewa.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private Status status;



}
