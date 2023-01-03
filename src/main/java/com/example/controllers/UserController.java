package com.example.controllers;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.User;
import com.example.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/users")
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

    @GetMapping(path = "getById")
    public User getById(@RequestParam("id") int id)
    {
        return userService.getById(id);
    }

    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestParam("email") String email, HttpServletResponse response)
    {
        User user = userService.getByEmail(email);
        String sessionId = UUID.randomUUID().toString();

        user.setSessionId(sessionId);
        userService.save(user);

        Cookie cookie = new Cookie("SESSION_ID", sessionId);
        cookie.setHttpOnly(false);  // allow to use in scripts
        cookie.setMaxAge(60 * 60);  // set cookie to expire in 1 hour
        cookie.setSecure(false);    // allow access from http
        cookie.setPath("/");        // allow access from every endpoint

        response.addCookie(cookie);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

    @PostMapping("logout")
    public void logout(@RequestParam("sessionId") String sessionId, HttpServletResponse response)
    {
        User user = userService.findBySessionId(sessionId);

        user.setSessionId(null);
        userService.save(user);
        
        Cookie cookie = new Cookie("SESSION_ID", "");
        cookie.setHttpOnly(false);  // allow to use in scripts
        cookie.setSecure(false);    // allow access from http
        cookie.setPath("/");        // allow access from every endpoint
        cookie.setMaxAge(0);  // Set the max age to 0 to delete the cookie

        // Set the cookie in the response header to delete it
        response.addCookie(cookie);
    }

    @PostMapping("/checkSession")
    public ResponseEntity<Void> checkSession(@RequestParam("sessionId") String sessionId)
    {
        User user = userService.findBySessionId(sessionId);
        if (user != null)
        {
            // Session identifier is valid, return 200 OK
            return ResponseEntity.ok().build();
        }
        else
        {
            // Session identifier is not valid, return 401 UNAUTHORIZED
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("getBySession")
    public User getBySessionId(@RequestParam("id") String sessionId)
    {
        return userService.findBySessionId(sessionId);
    }

    private final UserService userService;
}