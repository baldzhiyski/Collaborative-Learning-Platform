package com.softuni.client.service;


import com.softuni.client.domain.events.ConfirmAccountByRegisterEvent;

public interface NotificationService {

    void userRegistered(ConfirmAccountByRegisterEvent event);
}
