package com.example.controllers;

import com.example.services.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password-reminder")
@AllArgsConstructor
public class PasswordController {

    private final PasswordService service;

    /**
     * sends email to subject that corresponds to passed mail address
     * @param email subject mail address
     */
    @PostMapping(path = "send-email")
    public void sendEmail(@RequestParam("email") String email)
    {
        this.service.sendEmail(email);
    }

    /**
     * changes password to new based on remembered email
     * @param newPassword 
     */
    @PostMapping(path = "change-password")
    public void changePassword(@RequestParam("newPassword") String newPassword)
    {
        this.service.changePassword(newPassword, this.service.getEmail());
    }
}
