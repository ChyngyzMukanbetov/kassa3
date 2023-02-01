package com.example.kassa3.service.abstracts;

public interface EmailService {
    public void sendConfirmationEmail(String to, String confirmationToken);
}
