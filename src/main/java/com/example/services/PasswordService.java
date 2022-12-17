package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordService {

    private int otp;
    private String email;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    public void sendOTP(String email)
    {
        this.otp = new Random().nextInt(900000) + 100000;
        this.email = email;

        String message = "OTP = " + otp + "";
        String subject = "OTP";

        this.emailService.send(email, subject, message);
    }

    public void changePassword(String newPassword, String email)
    {
        this.userService.changePassword(newPassword, email);
    }

    public int getOtp() {
        return otp;
    }
    public String getEmail() {return email;}
}
