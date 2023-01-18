package com.example.repositories;

import java.util.List;
import java.util.Optional;

import com.example.models.User;

public interface UserRepository
{
    /**
     * @return list of all users
     */
    public List<User> findAll();
    /**
     * @param email user email
     * @return user that fits user email
     */
    public Optional<User> findByEmail(String email);
    /**
     * 
     * @param id user id
     * @return user that fits user id
     */
    public Optional<User> findById(int id);
    /**
     * @param id session id
     * @return user that fits session id
     */
    public Optional<User> findBySessionId(String id);
    /**
     * enables user based on passed id
     * @param id user id
     */
    public void enableUser(int id);
    /**
     * @return highest id from user collection
     */
    public int maxId();
    /**
     * saves new user to database
     * @param user
     */
    public void save(User user);
    /**
     * updates password to passed password for user of the passed email 
     * @param password new password
     * @param email user email
     */
    public void updatePassword(String password, String email);
}
