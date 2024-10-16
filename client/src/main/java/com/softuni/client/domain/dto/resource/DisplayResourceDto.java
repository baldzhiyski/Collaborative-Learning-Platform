package com.softuni.client.domain.dto.resource;

import com.softuni.client.domain.entity.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisplayResourceDto {
    private Long id;

    private String description;

    private ResourceType resourceType;

    private String pathToFile;

    private String youtubeUrlPath;
}
