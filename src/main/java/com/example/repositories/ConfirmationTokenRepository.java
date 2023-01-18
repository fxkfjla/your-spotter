package com.example.repositories;

import com.example.models.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenRepository
{
    /**
     * @param token unique UUID string that confirmation token contains
     * @return confirmation token that fits passed token
     */
    public Optional<ConfirmationToken> findByToken(String token);
    /**
     * confirms passed token
     * @param token
     */
    public void confirmToken(ConfirmationToken token);
    /**
     * @return highest id of confirmation token collection
     */
    public int maxId();
    /**
     * saves passed token
     * @param token
     */
    public void save(ConfirmationToken token);
}
