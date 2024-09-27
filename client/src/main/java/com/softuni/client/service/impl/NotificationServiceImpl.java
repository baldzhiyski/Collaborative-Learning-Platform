package com.softuni.client.service.impl;

import com.softuni.client.domain.entity.User;
import com.softuni.client.domain.entity.UserActivationLinkEntity;
import com.softuni.client.domain.events.ConfirmAccountByRegisterEvent;
import com.softuni.client.exceptions.ObjectNotFoundException;
import com.softuni.client.repository.UserActivationLinkRepository;
import com.softuni.client.repository.UserRepository;
import com.softuni.client.service.EmailService;
import com.softuni.client.service.NotificationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

import static com.softuni.client.utils.Messages.*;

@Service
public class NotificationServiceImpl implements NotificationService {
    private UserRepository userRepository;

    private EmailService emailService;

    private UserActivationLinkRepository userActivationLinkRepository;

    public NotificationServiceImpl(UserRepository userRepository, EmailService emailService, UserActivationLinkRepository userActivationLinkRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userActivationLinkRepository = userActivationLinkRepository;
    }

    @Override
    @EventListener(ConfirmAccountByRegisterEvent.class)
    public void userRegistered(ConfirmAccountByRegisterEvent event) {
        String activationCode = activationCode(event.getEmail());

        User savedUser = this.userRepository.findByEmail(event.getEmail()).orElseThrow(() -> new ObjectNotFoundException(USER_NOT_FOUND));

        emailService.sendRegistrationEmail(event.getEmail(),
                event.getUsername(),
                event.getFullName(),
                activationCode,savedUser);

    }

    public String activationCode(String email) {
        String activationCode = generateActivationCode();

        UserActivationLinkEntity userActivationCodeEntity = new UserActivationLinkEntity();
        userActivationCodeEntity.setActivationCode(activationCode);
        userActivationCodeEntity.setCreated(Instant.now());
        userActivationCodeEntity.setUserEntity(userRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(USER_NOT_FOUND)));

        userActivationLinkRepository.saveAndFlush(userActivationCodeEntity);

        return activationCode;

    }

    private static String generateActivationCode() {

        StringBuilder activationCode = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
            int randInx = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randInx));
        }

        return activationCode.toString();


    }
}
