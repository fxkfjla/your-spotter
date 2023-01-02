package com.example.services;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService
{
    public EmailService()
    {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost("smtp.gmail.com");
        mailSenderImpl.setPort(587);
        mailSenderImpl.setUsername("yourspotters@gmail.com");
        mailSenderImpl.setPassword("ynujqkzessheoiwa");

        Properties props = mailSenderImpl.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        mailSender = mailSenderImpl;
    }

    @Async
    public void send(String to, String subject, String mail)
    {
        try
        {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, "utf-8");
            helper.setTo(to);
            helper.setText(mail, true);
            helper.setSubject(subject);
            helper.setFrom("yourspotters@gmail.com");
            mailSender.send(msg);
        }
        catch(MessagingException e)
        {
            logger.error("Failed to send email.", e);
            throw new IllegalStateException("Failed to send email.");
        }
    }

    private final JavaMailSender mailSender;    
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
}