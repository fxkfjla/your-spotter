package com.example.services;

import com.example.controllers.UserController;
import com.example.models.RegistrationRequest;
import com.example.models.UserRole;
import com.example.models.User;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistrationService
{
    public String register(RegistrationRequest request) 
    {
        boolean emailIsValid = emailValidator.test(request.getEmail());

        if(!emailIsValid)
        {
            // TODO: Handle exception
        }

        return userController.signUpUser
        (
            new User(request.getEmail(), request.getPassword(), UserRole.USER)
        );
    }   

    private final EmailValidator emailValidator;
    private final UserController userController;
}
