package com.softuni.client.service.impl;

import com.softuni.client.domain.entity.User;
import com.softuni.client.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final String platformEmail;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender, @Value("${mail.platformEmail}") String platformEmail) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.platformEmail = platformEmail;
    }

    @Override
    public void sendRegistrationEmail(String email, String username, String fullName, String activationCode, User savedUser) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom(platformEmail);
            mimeMessageHelper.setReplyTo(platformEmail);
            mimeMessageHelper.setSubject("Welcome to E-Learning Platform!");
            mimeMessageHelper.setText(generateRegistrationEmailBody(username, activationCode,fullName,email), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateRegistrationEmailBody(String username, String activationCode, String fullName, String email) {

        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("activation_code", activationCode);
        context.setVariable("fullName", fullName);
        context.setVariable("email" , email);

        return templateEngine.process("email/registration-email", context);

    }


}
