package com.example.controllers;

import org.springframework.stereotype.Controller;

import com.example.models.User;
import com.example.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class UserController
{
    public String signUpUser(User user)
    {
        return userService.signUpUser(user);
    }

    private final UserService userService;    
}
