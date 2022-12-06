package com.example.controllers;

import com.example.models.User;
import com.example.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/users")
// @CrossOrigin
@RestController
public class UserController
{
    @GetMapping(path = "all")
    public List<User> getAll()
    {
        return userService.getAll();
    }

    @GetMapping(path = "lookup")
    public User getByEmail(@RequestParam("email") String email)
    {
        return userService.getByEmail(email);
    }

    @GetMapping(path = "lookup/compare")
    public boolean compare(@RequestParam("email") String email, @RequestParam("password") String password)
    {
        return userService.compare(email, password);
    }

    private final UserService userService;
}
