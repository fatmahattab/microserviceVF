package com.fatma.users.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;

@Service
public class MailService {



    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;



    public void sendEmail(String email, String message, String subject) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(message);
        mailSender.send(msg);

    }
}
