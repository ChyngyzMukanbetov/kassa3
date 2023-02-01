package com.example.kassa3.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.example.kassa3.service.abstracts.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendConfirmationEmail(String to, String confirmationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Registration Confirmation");
        message.setText("Please click on the following link to confirm your registration: "
                + "http://localhost:8080/confirm-registration?token=" + confirmationToken);
        emailSender.send(message);
    }
}
