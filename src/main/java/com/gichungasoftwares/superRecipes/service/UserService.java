package com.gichungasoftwares.superRecipes.service;

import com.gichungasoftwares.superRecipes.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User findUserById(Long id) throws Exception;
}
