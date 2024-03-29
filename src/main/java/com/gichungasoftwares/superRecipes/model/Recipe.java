package com.gichungasoftwares.superRecipes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    private String title;

    @ManyToOne
    private User user;

    private String image;

    private String description;

    private boolean isVegetarian;

    private List<Long> likes = new ArrayList<>();

    private LocalDateTime createdAt;
}
