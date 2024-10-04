package com.softuni.universities.service;

import com.softuni.universities.domain.dto.UniversityDto;

import java.util.List;

public interface UniversityService {

    void createUniversity(UniversityDto universityDto);

    List<UniversityDto> findAllUniversities();

    UniversityDto getUniversityByName(String name);
}
