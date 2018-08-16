package com.app.controllers;


import com.app.exceptions.UsernameExistsException;
import com.app.model.User;
import com.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/register")
    public String register(Model model) {

        model.addAttribute("User", new User());

        return "register";
    }


    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute("User") @Valid User user,
                               BindingResult result, Model model) {
        User newUser = this.createUserAccount(user, result);

            if (newUser == null) {
                result.addError(new FieldError("User", "username",
                        "There is already an account with that username: " + user.getUsername()));
            } else {
                model.addAttribute("registerSuccessful", true);
            }



        return "register";
    }

    private User createUserAccount(User user, BindingResult result) {
        User registered;
        try {
            registered = userService.registerNewUserAccount(user);
        } catch (UsernameExistsException e) {
            return null;
        }
        return registered;
    }
}
