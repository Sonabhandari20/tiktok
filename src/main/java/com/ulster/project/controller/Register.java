package com.ulster.project.controller;


import com.ulster.project.models.User;  // Correct import
import com.ulster.project.models.UserSignUpRequest;
import com.ulster.project.repository.UserRepository;
import com.ulster.project.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Register {

    @Autowired
    private UserRepository userRepository; 
    @Autowired
    private UserService userService;
    // Inject the UserRepository to interact with the database
    @PostMapping("/register")
    public ModelAndView handleSignUp(@ModelAttribute UserSignUpRequest signUpRequest) {
        // Check if user already exists by email
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            // Return the sign-up form with an error message if the email is already taken
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("signup");
            modelAndView.addObject("error", "Email already exists!");  // Error message
            return modelAndView;
        }

        // Call the registerUser method to save the new user to the database
        User newUser = new User();
        newUser.setUsername(signUpRequest.getUsername());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(signUpRequest.getPassword());

       userService.registerUser(newUser);  // This will hash the password and save the user

        // Redirect to the sign-in page after successful registration
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/signin");  // Redirect to the sign-in page
        return modelAndView;
    }
    @PostMapping("/signin")
    public ModelAndView handleSignIn(@ModelAttribute("username") String username,
                                     @ModelAttribute("password") String password) {
        ModelAndView modelAndView = new ModelAndView();

        // Fetch user from the database by username
        User user = userService.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // If credentials are valid, redirect to the dashboard or home page
            modelAndView.setViewName("redirect:/home");
        } else {
            // If credentials are invalid, reload the sign-in page with an error message
            modelAndView.setViewName("signin");
            modelAndView.addObject("error", "Invalid username or password. Please try again.");
        }

        return modelAndView;
    }
}

    

