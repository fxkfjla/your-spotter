package com.example.repositories;

import com.example.models.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenRepository
{
    public Optional<ConfirmationToken> findByToken(String token);
    public void confirmToken(ConfirmationToken token);
    public int maxId();
    public void save(ConfirmationToken token);
}
