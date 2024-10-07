package com.softuni.client.service;

import com.softuni.client.domain.entity.University;

import java.util.Optional;

public interface UniService {

    Optional<University> getUniByName(String name);
}
