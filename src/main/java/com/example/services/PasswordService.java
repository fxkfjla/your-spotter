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
        String link = "http://localhost:8080/change-password.html";
        String message = "<a href=\"" + link + "\">Change password</a>";
        this.emailService.send(email, subject, message);
    }

    public void changePassword(String newPassword, String email)
    {
        this.userService.changePassword(newPassword, email);
        this.email = "";
    }

    public String getEmail() {return email;}
}
