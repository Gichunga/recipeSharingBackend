package com.gichungasoftwares.superRecipes.service;

import com.gichungasoftwares.superRecipes.config.JwtProvider;
import com.gichungasoftwares.superRecipes.model.User;
import com.gichungasoftwares.superRecipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User createUser(User user) throws Exception {
        User savedUser = userRepository.findByEmail(user.getEmail());
        if(savedUser != null){
            throw new Exception("User with the provided email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Long id) throws Exception {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user, Long id) throws Exception {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if(user.getEmail() != null){
            existingUser.setEmail(user.getEmail());
        }
        if(user.getFullName() != null){
            existingUser.setFullName(user.getFullName());
        }
        if (user.getPassword() != null){
            existingUser.setPassword(user.getPassword());
        }
        if (user.getImage() != null){
            existingUser.setImage(user.getImage());
        }
        return userRepository.save(existingUser);
    }

    @Override
    public User findUserById(Long id) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new Exception("User with id " + id + " was not found! ");
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        if(email == null){
            throw new Exception("Token is invalid");
        }

        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new Exception("User not found with email " + email);
        }
        return user;

    }
}
