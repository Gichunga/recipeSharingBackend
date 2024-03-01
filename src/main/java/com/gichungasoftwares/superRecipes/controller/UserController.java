package com.gichungasoftwares.superRecipes.controller;

import com.gichungasoftwares.superRecipes.model.User;
import com.gichungasoftwares.superRecipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception {
        User savedUser = userRepository.findByEmail(user.getEmail());
        if(savedUser == null){
            throw new Exception("No user found");
        }
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) throws Exception {
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }

    @GetMapping("/users")
    public List<User> allUsers(){
        return userRepository.findAll();
    }

}
