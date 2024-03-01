package com.gichungasoftwares.superRecipes.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private User user;
    private String image;
    private String description;
    private boolean isVegetarian;
    private List<Long> likes = new ArrayList<>();
    private LocalDateTime createdAt;
}
