package com.softuni.client.service;

import com.softuni.client.domain.dto.course.CourseDto;
import com.softuni.client.domain.dto.resource.AddResourceDto;
import com.softuni.client.domain.dto.resource.DisplayResourceDto;
import com.softuni.client.domain.entity.University;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UniService {

    Optional<University> getUniByName(String name);

    CourseDto getCourse(UUID courseUuid);

    List<DisplayResourceDto> getMaterialsForCourse(UUID courseUuid);

    DisplayResourceDto saveResource(UUID courseUuid, String loggedInUserId, AddResourceDto resourceDto);
}
