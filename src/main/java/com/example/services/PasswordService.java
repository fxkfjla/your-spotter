package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private String email;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    public void sendEmail(String email)
    {
        this.email = email;
        String subject = "Password notification";
        String message = "http://localhost:8080/change-password.html";
        this.emailService.send(email, subject, message);
    }

    public void changePassword(String newPassword, String email)
    {
        this.userService.changePassword(newPassword, email);
    }

    public String getEmail() {return email;}
}
