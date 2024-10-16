package com.softuni.client.domain.dto.resource;

import com.softuni.client.domain.entity.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
public class AddResourceDto  implements Serializable {

    private String description;

    private String resourceType;

    private MultipartFile multipartFile;

    private String youtubeUrlPath;
}
