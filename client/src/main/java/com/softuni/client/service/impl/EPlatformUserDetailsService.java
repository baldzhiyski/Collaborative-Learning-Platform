package com.softuni.client.service.impl;

import com.softuni.client.domain.entity.Role;
import com.softuni.client.domain.entity.User;
import com.softuni.client.domain.user_details.StudyPlatformUserDetails;
import com.softuni.client.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class EPlatformUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    public EPlatformUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }

    private UserDetails map(User userEntity) {
        return new StudyPlatformUserDetails(
                userEntity.getUsername(),
                userEntity.getPassword(),
                mapAuthorities(userEntity.getRoles()),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getUuid(),
                userEntity.getId()
        );
    }

    private Collection<? extends GrantedAuthority> mapAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleType()))
                .collect(Collectors.toSet());
    }
}
