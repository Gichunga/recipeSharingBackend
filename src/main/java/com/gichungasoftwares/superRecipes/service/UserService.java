package com.gichungasoftwares.superRecipes.service;

import com.gichungasoftwares.superRecipes.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User createUser(User user) throws Exception;

    String deleteUser(Long id) throws Exception;

    List<User> allUsers();

    User updateUser(User user, Long id) throws Exception;

    User findUserById(Long id) throws Exception;

}
