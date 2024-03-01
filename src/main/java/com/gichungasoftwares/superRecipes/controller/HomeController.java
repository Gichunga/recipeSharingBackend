package com.gichungasoftwares.superRecipes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homeController()
    {
        return "welcome to recipe sharing app";
    }
}
