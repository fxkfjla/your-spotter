package com.example.services;

import com.example.models.ConfirmationToken;
import com.example.models.RegistrationRequest;
import com.example.models.UserRole;
import com.example.models.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

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

        return userService.signUpUser
        (
            new User(request.getEmail(), request.getPassword(), UserRole.USER)
        );
    }   

    @Transactional
    public String confirm(String token)
    {
        ConfirmationToken confirmationToken = tokenService.getToken(token);

        if(confirmationToken.getConfirmedAt() != null)
        {
            return "email already confirmed";
        }

        LocalDateTime expiresAt = confirmationToken.getExpiresAt();
        if(expiresAt.isBefore(LocalDateTime.now()))
        {
            // TODO: Handle exception
            return "token expired";
        }

        tokenService.setConfirmedAt(confirmationToken);
        userService.enableUser(confirmationToken.getUser().getEmail());

        return "confirmed";
    }

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final ConfirmationTokenService tokenService;
}
