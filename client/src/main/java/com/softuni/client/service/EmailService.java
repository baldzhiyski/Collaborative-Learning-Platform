package com.softuni.client.service;

import com.softuni.client.domain.entity.User;

public interface EmailService {
    void sendRegistrationEmail(
            String email,
            String username,String fullName,
            String activationCode, User savedUser);
}
