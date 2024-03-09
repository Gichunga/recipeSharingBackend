package com.gichungasoftwares.superRecipes.controller;

import com.gichungasoftwares.superRecipes.config.JwtProvider;
import com.gichungasoftwares.superRecipes.model.User;
import com.gichungasoftwares.superRecipes.repository.UserRepository;
import com.gichungasoftwares.superRecipes.request.LoginRequest;
import com.gichungasoftwares.superRecipes.response.AuthResponse;
import com.gichungasoftwares.superRecipes.service.CustomUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsImpl customUserDetails;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();

        // check if user already exists
        User isEmailExisting = userRepository.findByEmail(email);
        if(isEmailExisting != null){
            throw new Exception("A user already exists with the provided email address" + email);
        }

        // set user details
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullName(fullName);

        // save user to the database
        User savedUser = userRepository.save(createdUser);

        // create authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        // set authentication to context holder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generate token
        String token = jwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("Signup was successful");
        return res;
    }

    @PostMapping("/login")
    public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        // set authentication to context holder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generate jwt token
        String token = jwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("Login was successful");
        return res;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if (userDetails == null){
            throw new BadCredentialsException("User not found");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword() )) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
