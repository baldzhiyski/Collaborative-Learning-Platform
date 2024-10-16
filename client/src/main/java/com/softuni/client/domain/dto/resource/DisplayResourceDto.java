package com.softuni.client.domain.dto.resource;

import com.softuni.client.domain.entity.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisplayResourceDto {

    private String description;

    private ResourceType resourceType;

    private String pathToFile;

    private String youtubeUrl;
}
