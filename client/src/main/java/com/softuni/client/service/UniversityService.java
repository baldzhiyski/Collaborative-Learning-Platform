package com.softuni.client.service;

import com.softuni.client.domain.dto.course.CourseDto;
import com.softuni.client.domain.dto.university.UniversityDto;

import java.util.List;

public interface UniversityService {
    List<UniversityDto> getAllUniversities();

    void publishUniversity(UniversityDto addUniDto);

    UniversityDto findByName(String universityName);
}
