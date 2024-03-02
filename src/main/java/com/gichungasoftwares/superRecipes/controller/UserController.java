package com.gichungasoftwares.superRecipes.controller;

import com.gichungasoftwares.superRecipes.model.User;
import com.gichungasoftwares.superRecipes.repository.UserRepository;
import com.gichungasoftwares.superRecipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) throws Exception {
        return userService.deleteUser(userId);
    }

    @GetMapping("/users")
    public List<User> allUsers(){
        return userService.allUsers();
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) throws Exception {
        return userService.updateUser(user, id);
    }
}
