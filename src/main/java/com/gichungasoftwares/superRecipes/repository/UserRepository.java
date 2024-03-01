package com.gichungasoftwares.superRecipes.repository;

import com.gichungasoftwares.superRecipes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
