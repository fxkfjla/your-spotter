package com.example.repositories;

import com.example.models.ConfirmationToken;

import lombok.AllArgsConstructor;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class ConfirmationTokenRepositoryImpl implements ConfirmationTokenRepository
{
    @Override
    public Optional<ConfirmationToken> findByToken(String token)
    {
        return Optional.of(new ConfirmationToken());
    }

    @Override
    public void save(ConfirmationToken token)
    {
        mongoTemplate.save(token);
    }

    private final MongoTemplate mongoTemplate;
}
