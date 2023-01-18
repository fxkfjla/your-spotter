package com.example.controllers;

import com.example.services.RegistrationService;
import com.example.utility.RegistrationRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController
{
    /**
     * registers new user based on passed registration request
     * @param request utility class that contains email and password
     * @return debug string that notifies if registration was successful or not
     */
    @ResponseBody
    @PostMapping
    public String register(@RequestBody RegistrationRequest request)
    {
        return registrationService.register(request);
    }

    /**
     * user that corresponds passed token
     * @param token
     * @return debug string that notifies if confirmation was successful or not
     */
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token)
    {
        return registrationService.confirm(token);
    }

    private final RegistrationService registrationService;
}