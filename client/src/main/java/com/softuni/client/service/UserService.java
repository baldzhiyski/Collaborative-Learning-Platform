package com.softuni.client.service;

import com.softuni.client.domain.dto.user.RegisterDto;
import com.softuni.client.domain.entity.User;

public interface UserService {

    Boolean isSubscribed(Long loggedUserId);

    void registerUser(RegisterDto userRegisterDto);

    void activateAccount(String activationCode);

    User getUser(String username);

    void sendFeedBack(User loggedUser, String feedback);

    void subscribe(User logged);
}
