package com.example.repositories;

import java.util.List;
import java.util.Optional;

import com.example.models.User;

public interface UserRepository
{
    public List<User> findAll();
    public Optional<User> findByEmail(String email);
    public void save(User user);
}
