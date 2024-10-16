package com.softuni.client.service.impl;

import com.softuni.client.domain.dto.course.CourseDto;
import com.softuni.client.domain.dto.resource.AddResourceDto;
import com.softuni.client.domain.dto.resource.DisplayResourceDto;
import com.softuni.client.domain.entity.Course;
import com.softuni.client.domain.entity.Resource;
import com.softuni.client.domain.entity.University;
import com.softuni.client.domain.entity.User;
import com.softuni.client.domain.entity.enums.ResourceType;
import com.softuni.client.exceptions.ObjectNotFoundException;
import com.softuni.client.repository.CourseRepository;
import com.softuni.client.repository.ResourceRepository;
import com.softuni.client.repository.UniversityRepository;
import com.softuni.client.repository.UserRepository;
import com.softuni.client.service.CloudinaryService;
import com.softuni.client.service.UniService;
import com.softuni.client.utils.Messages;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UniServiceImpl  implements UniService {
    private UniversityRepository universityRepository;
    private CourseRepository courseRepository;

    private UserDetailsService userDetailsService;
    private ResourceRepository resourceRepository;
    private CloudinaryService cloudinaryService;

    private UserRepository userRepository;

    private ModelMapper mapper;
    public UniServiceImpl(UniversityRepository universityRepository, CourseRepository courseRepository, UserDetailsService userDetailsService, ResourceRepository resourceRepository, CloudinaryService cloudinaryService, UserRepository userRepository, ModelMapper mapper) {
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
        this.userDetailsService = userDetailsService;
        this.resourceRepository = resourceRepository;
        this.cloudinaryService = cloudinaryService;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<University> getUniByName(String name) {
        return universityRepository.findByName(name);
    }

    @Override
    public CourseDto getCourse(UUID courseUuid) {
       return Course.toDto(getCourseViaUUID(courseUuid));
    }

    @Override
    public List<DisplayResourceDto> getMaterialsForCourse(UUID courseUuid) {
        Course course = getCourseViaUUID(courseUuid);

        return course.getResources()
                .stream()
                .map(resource -> this.mapper.map(resource, DisplayResourceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DisplayResourceDto saveResource(UUID courseUuid, String loggedInUserName, AddResourceDto resourceDto) {
        Course course = getCourseViaUUID(courseUuid);
        Resource resource = new Resource();
        User user = userRepository.findByUsername(loggedInUserName).orElseThrow();

        System.out.println(resourceDto.getResourceType());
        if(resourceDto.getResourceType().equals("YOUTUBE")){
        resource.setResourceType(ResourceType.VIDEO);
        resource.setYoutubeUrlPath(resourceDto.getYoutubeUrlPath());
        }else {
            resource.setResourceType(ResourceType.DOCUMENT);
        }
        resource.setDescription(resourceDto.getDescription());
        resource.setUuid(UUID.randomUUID());
        resource.setUploadedBy(user);
        resource.setCourse(course);

        // If it's a file resource, save the file locally
        if (resourceDto.getResourceType().equals("DOCUMENT") && !resourceDto.getMultipartFile().isEmpty()) {
            // Define the base directory for resources
            String baseDirectory = "client/src/main/resources/materials";  // Adjust the path as necessary
            // Create course directory
            String uniName = course.getUniversity().getName();
            String courseDirectoryName = course.getName().replaceAll("\\s+", "_"); // Replace spaces with underscores
            String courseDirectoryPath = baseDirectory + "/"+ uniName + "/" + courseDirectoryName;

            // Create directories if they do not exist
            File courseDirectory = new File(courseDirectoryPath);
            if (!courseDirectory.exists()) {
                courseDirectory.mkdirs();  // Create directories
            }

            // Set the file name and path
            String fileName = resourceDto.getMultipartFile().getOriginalFilename();
            Path filePath = Paths.get(courseDirectoryPath, fileName);

            try {
                // Save the file locally
                Files.copy(resourceDto.getMultipartFile().getInputStream(), filePath);
                // Save the local path in the database
                resource.setPathToFile(filePath.toString());
            } catch (IOException e) {
                e.printStackTrace(); // Handle exceptions as appropriate
                throw new RuntimeException("Failed to save the file: " + e.getMessage());
            }
        }
        // Save resource to database
        resourceRepository.saveAndFlush(resource);

        // Return the DTO of the saved resource
        return new DisplayResourceDto(resource.getId(),resource.getDescription(), resource.getResourceType(), resource.getPathToFile(),resource.getYoutubeUrlPath());
    }

    private Course getCourseViaUUID(UUID courseUuid) {
        return this.courseRepository.findByUuid(courseUuid).orElseThrow(() -> new ObjectNotFoundException(Messages.INVALID_COURSE));
    }
}
