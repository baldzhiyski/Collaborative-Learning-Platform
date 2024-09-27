package com.softuni.client.service;

import com.softuni.client.domain.dto.user.RegisterDto;

public interface UserService {

    Boolean isSubscribed(Long loggedUserId);

    void registerUser(RegisterDto userRegisterDto);

    void activateAccount(String activationCode);
}
