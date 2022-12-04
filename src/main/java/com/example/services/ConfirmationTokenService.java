package com.example.services;

import com.example.models.ConfirmationToken;
import com.example.repositories.ConfirmationTokenRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService
{
    public void saveConfirmationToken(ConfirmationToken token)
    {
        repository.save(token);
    }

    private final ConfirmationTokenRepository repository;
}
