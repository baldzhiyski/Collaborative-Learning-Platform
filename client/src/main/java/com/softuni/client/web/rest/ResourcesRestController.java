package com.softuni.client.web.rest;

import com.softuni.client.domain.dto.resource.AddResourceDto;
import com.softuni.client.domain.dto.resource.DisplayResourceDto;
import com.softuni.client.service.UniService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ResourcesRestController {
    private UniService uniService;

    public ResourcesRestController(UniService uniService) {
        this.uniService = uniService;
    }


    @GetMapping("/resources/{courseUuid}")
    public ResponseEntity<List<DisplayResourceDto>> getResources(@PathVariable UUID courseUuid) {
        List<DisplayResourceDto> materials = uniService.getMaterialsForCourse(courseUuid);
        return ResponseEntity.ok(materials);
    }

    @PostMapping(value = "/resources/upload/{loggedInUser}/user/{courseUuid}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DisplayResourceDto> publishResource(
            @PathVariable UUID courseUuid,
            @PathVariable String loggedInUser,
            @ModelAttribute AddResourceDto resourceDto) {

        // Optional: Validate the input here (e.g., check if resourceDto is valid)

        // Call your service to save the resource (assuming there's a method to handle it)
        DisplayResourceDto savedResource = uniService.saveResource(courseUuid, loggedInUser, resourceDto);

        // Return the saved resource with a 201 Created status
        return ResponseEntity.status(HttpStatus.CREATED).body(savedResource);
    }
}
