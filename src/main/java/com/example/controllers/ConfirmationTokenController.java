package com.example.controllers;

import com.example.models.ConfirmationToken;
import com.example.services.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmationTokenController
{
    public void saveConfirmationToken(ConfirmationToken token)
    {
        confirmationTokenService.save(token);
    } 

    private final ConfirmationTokenService confirmationTokenService;
}
