package com.softuni.client.domain.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ConfirmAccountByRegisterEvent  extends ApplicationEvent {
    private final String email;
    private final String username;

    private final String fullName;

    public ConfirmAccountByRegisterEvent(Object source, String email, String username, String fullName) {
        super(source);
        this.email = email;
        this.username = username;
        this.fullName = fullName;
    }
}
