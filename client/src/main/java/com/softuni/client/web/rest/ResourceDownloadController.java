package com.softuni.client.web.rest;

import com.softuni.client.repository.ResourceRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/download")
public class ResourceDownloadController {

    // TODO : Refactoring
    private ResourceRepository resourceRepository;
    private final ResourceLoader resourceLoader;

    // Inject the ResourceLoader to load files from the filesystem
    public ResourceDownloadController(ResourceRepository resourceRepository, ResourceLoader resourceLoader) {
        this.resourceRepository = resourceRepository;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long resourceId) {
        try {
            com.softuni.client.domain.entity.Resource res = this.resourceRepository.findById(resourceId)
                    .orElseThrow();

            // Define the path to the file
            Path filePath = Paths.get(res.getPathToFile()).normalize();
            Resource resource = resourceLoader.getResource("file:" + filePath.toString());

            // Check if the file exists
            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            // Determine content type
            String contentType = "application/octet-stream"; // Default to binary content
            try {
                contentType = Files.probeContentType(filePath); // Automatically determine the file type
            } catch (IOException ex) {
                System.out.println("Could not determine file type.");
            }

            // Prepare the headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

            // Return the file as a response entity with the content type
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .headers(headers)
                    .body(resource);

        } catch (Exception e) {
            // Handle any exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}