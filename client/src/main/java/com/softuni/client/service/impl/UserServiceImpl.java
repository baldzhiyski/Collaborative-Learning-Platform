package com.softuni.client.service.impl;

import com.softuni.client.exceptions.ObjectNotFoundException;
import com.softuni.client.repository.UserRepository;
import com.softuni.client.service.UserService;
import org.springframework.stereotype.Service;

import static com.softuni.client.utils.Messages.USER_NOT_FOUND;

@Service
public class UserServiceImpl  implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean isSubscribed(Long loggedUserId) {
        return this.userRepository.findById(loggedUserId).orElseThrow(()-> new ObjectNotFoundException(USER_NOT_FOUND)).getSubscribed();
    }
}
