package com.softuni.client.service.impl;

import com.softuni.client.domain.entity.University;
import com.softuni.client.repository.UniversityRepository;
import com.softuni.client.service.UniService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniServiceImpl  implements UniService {
    private UniversityRepository universityRepository;

    public UniServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public Optional<University> getUniByName(String name) {
        return universityRepository.findByName(name);
    }
}
