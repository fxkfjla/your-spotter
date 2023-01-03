package com.example.repositories;

import java.util.List;
import java.util.Optional;

import com.example.models.User;

public interface UserRepository
{
    public List<User> findAll();
    public Optional<User> findByEmail(String email);
    public Optional<User> findById(int id);
    public Optional<User> findBySessionId(String id);
    public void enableUser(int id);
    public int maxId();
    public void save(User user);
    public void updatePassword(String password, String email);
}
