package com.example.kassa3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication(exclude = {SessionAutoConfiguration.class,
        SecurityAutoConfiguration.class})
public class Kassa3Application {

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setProtocol("smtp");
        javaMailSender.setHost("127.0.0.1");
        javaMailSender.setPort(25);

        return javaMailSender;
    }

    public static void main(String[] args) {
        SpringApplication.run(Kassa3Application.class, args);
    }

}
