package com.ulster.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ulster.project.models.AdminLoginRequest;

@Controller
public class AdminController {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";

    // Show login page first when the user visits the root URL
    @GetMapping("/")
    public String showLoginPage() {
        return "account";// Make sure the login page is named "login.html"
    }
    @GetMapping("/signin")
    public String showSignInPage() {
        return "signin";
    }
    @GetMapping("/home")
    public String showSignInHome() {
        return "index";
    }
    @GetMapping("/signup")
    public ModelAndView showSignUpPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");  // Display the sign-up form
        return modelAndView;
    }
        
    @PostMapping("/login")
    public ModelAndView adminLogin(@ModelAttribute AdminLoginRequest loginRequest) {
        System.out.println("Received username: " + loginRequest.getAdminUsername());
        System.out.println("Received password: " + loginRequest.getAdminPassword());

        // Check credentials
        if ("admin".equals(loginRequest.getAdminUsername()) && "password".equals(loginRequest.getAdminPassword())) {
            // Redirect to the dashboard if credentials are valid
            return new ModelAndView(new RedirectView("/admin/dashboard"));
        } else {
            // Redirect back to the login page with an error message for invalid credentials
            ModelAndView modelAndView = new ModelAndView("admin");
            modelAndView.addObject("message", "Invalid credentials");
            return modelAndView;
        }
    }


    @GetMapping("/admin/dashboard")
    public String showDashboard() {
        return "index"; // Redirect to the admin dashboard view after login
    }
}
