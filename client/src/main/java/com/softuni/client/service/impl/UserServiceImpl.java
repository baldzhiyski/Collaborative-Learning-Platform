package com.softuni.client.service.impl;

import com.softuni.client.domain.dto.user.RegisterDto;
import com.softuni.client.domain.entity.FeedBackComment;
import com.softuni.client.domain.entity.Role;
import com.softuni.client.domain.entity.User;
import com.softuni.client.domain.entity.UserActivationLinkEntity;
import com.softuni.client.domain.entity.enums.RoleType;
import com.softuni.client.domain.events.ConfirmAccountByRegisterEvent;
import com.softuni.client.exceptions.ObjectNotFoundException;
import com.softuni.client.repository.FeedBackRepository;
import com.softuni.client.repository.RoleRepository;
import com.softuni.client.repository.UserActivationLinkRepository;
import com.softuni.client.repository.UserRepository;
import com.softuni.client.service.CloudinaryService;
import com.softuni.client.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    private UserDetailsService userDetailsService;
    private ApplicationEventPublisher applicationEventPublisher;
    private UserRepository userRepository;

    private FeedBackRepository feedBackRepository;

    private CloudinaryService cloudinaryService;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;


    private UserActivationLinkRepository userActivationLinkRepository;
    private ModelMapper mapper;

    public UserServiceImpl(UserDetailsService userDetailsService, ApplicationEventPublisher applicationEventPublisher, UserRepository userRepository, FeedBackRepository feedBackRepository, CloudinaryService cloudinaryService, RoleRepository roleRepository, UserActivationLinkRepository userActivationLinkRepository, ModelMapper mapper) {
        this.userDetailsService = userDetailsService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.userRepository = userRepository;
        this.feedBackRepository = feedBackRepository;
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
        mapped.setRoles(new HashSet<>());
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
        roles.add(roleRepository.findByRoleType(RoleType.USER).orElseThrow(()->new ObjectNotFoundException(INVALID_ROLE_TYPE)));
        user.setRoles(roles);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public void sendFeedBack(User loggedUser, String feedback) {
        FeedBackComment feedBackComment = new FeedBackComment();
        feedBackComment.setUuid(UUID.randomUUID());
        feedBackComment.setAuthor(loggedUser);
        feedBackComment.setFeedback(feedback);

        this.feedBackRepository.saveAndFlush(feedBackComment);
    }

    @Override
    public void subscribe(User logged) {
        logged.setSubscribed(true);
        this.userRepository.saveAndFlush(logged);
    }

    @Override
    @Transactional
    public void createUserIfNotExist(String username, String email, String fullName, String address) {
        if(this.userRepository.findByUsername(username).isEmpty()) {
            User user = new User();
            user.setUuid(UUID.randomUUID());

            String firstName = fullName.split("\\s+")[0];
            String lastName = fullName.split("\\s+")[1];

            // Set Random strong pass and the user will receive email that he/she needs to change it
            user.setPassword(String.valueOf(UUID.randomUUID()));
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleType(RoleType.USER).orElseThrow(() -> new ObjectNotFoundException(INVALID_ROLE_TYPE)));
            user.setRoles(roles);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setEmail(email);
            user.setProfilePictureUrl("images/e-learn.jpeg");

            this.userRepository.saveAndFlush(user);
        }
    }

    @Override
    public Authentication login(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return auth;
    }

}
