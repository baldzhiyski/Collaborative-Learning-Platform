package com.softuni.client.domain.dto.course;

import com.softuni.client.domain.entity.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
public class CourseUploadDto {
    private UUID uuid;
    private String name;

    private String description;

    private String degree;

    private String language;

    private MultipartFile file;

    private ResourceType resourceType;

}
