package com.example.services;

import com.example.models.ConfirmationToken;
import com.example.repositories.ConfirmationTokenRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService
{
    public void save(ConfirmationToken token)
    {
        token.setId(repository.maxId() + 1);
        repository.save(token);
    }

    ConfirmationToken getToken(String token)
    {
        Optional<ConfirmationToken> confirmationToken = repository.findByToken(token);

        if(!confirmationToken.isPresent())
        {
            // TODO: Handle exception
        }

        return confirmationToken.get();
    }

    public void confirmToken(ConfirmationToken token)
    {
        repository.confirmToken(token);
    }

    private final ConfirmationTokenRepository repository;
}
