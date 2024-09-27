package com.softuni.client.service.impl;

import com.softuni.client.domain.dto.user.RegisterDto;
import com.softuni.client.domain.entity.Role;
import com.softuni.client.domain.entity.User;
import com.softuni.client.domain.entity.UserActivationLinkEntity;
import com.softuni.client.domain.entity.enums.RoleType;
import com.softuni.client.domain.events.ConfirmAccountByRegisterEvent;
import com.softuni.client.exceptions.ObjectNotFoundException;
import com.softuni.client.repository.RoleRepository;
import com.softuni.client.repository.UserActivationLinkRepository;
import com.softuni.client.repository.UserRepository;
import com.softuni.client.service.CloudinaryService;
import com.softuni.client.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.softuni.client.utils.Messages.*;

@Service
public class UserServiceImpl  implements UserService {

    private ApplicationEventPublisher applicationEventPublisher;
    private UserRepository userRepository;

    private CloudinaryService cloudinaryService;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;


    private UserActivationLinkRepository userActivationLinkRepository;
    private ModelMapper mapper;

    public UserServiceImpl(ApplicationEventPublisher applicationEventPublisher, UserRepository userRepository, CloudinaryService cloudinaryService, RoleRepository roleRepository,  UserActivationLinkRepository userActivationLinkRepository, ModelMapper mapper) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userActivationLinkRepository = userActivationLinkRepository;
        this.mapper = mapper;
    }

    @Override
    public Boolean isSubscribed(Long loggedUserId) {
        return this.userRepository.findById(loggedUserId).orElseThrow(()-> new ObjectNotFoundException(USER_NOT_FOUND)).getSubscribed();
    }

    @Override
    public void registerUser(RegisterDto userRegisterDto) {
        MultipartFile profilePictureFile = userRegisterDto.getProfilePicture();

        String imageUrl = cloudinaryService.uploadPhoto(profilePictureFile, "users-profilePics");
        User mapped = this.mapper.map(userRegisterDto, User.class);
        mapped.setPassword(passwordEncoder.encode(mapped.getPassword()));
        mapped.setProfilePictureUrl(imageUrl);
        mapped.setUuid(UUID.randomUUID());

        this.userRepository.saveAndFlush(mapped);
        applicationEventPublisher.publishEvent(new ConfirmAccountByRegisterEvent("UserService",
                mapped.getEmail(),
                mapped.getUsername(),
                mapped.fullName()));

    }

    @Override
    @Transactional
    public void activateAccount(String activationCode) {

        UserActivationLinkEntity userActivationLinkEntity = this.userActivationLinkRepository.findByActivationCode(activationCode).orElseThrow(() -> new ObjectNotFoundException("No such code in the db"));

        User user = userActivationLinkEntity.getUserEntity();
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleType(RoleType.USER).orElseThrow());
        user.setRoles(roles);
        this.userRepository.saveAndFlush(user);
    }
}
