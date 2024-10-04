package com.softuni.universities.controllers;

import com.softuni.universities.domain.dto.UniversityDto;
import com.softuni.universities.domain.dto.CourseDto; // Ensure you import CourseDto
import com.softuni.universities.exception.ObjectNotFoundException;
import com.softuni.universities.service.UniversityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@Tag(name = "Universities API", description = "API for managing universities.")
public class UniversitiesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversitiesController.class);

    private final UniversityService universityService;

    public UniversitiesController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Operation(
            summary = "Retrieve university details by ID",
            description = "Fetch the details of a specific university using its unique identifier, including associated courses.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "University details retrieved successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UniversityDto.class),
                                    examples = @ExampleObject(
                                            name = "Sample University",
                                            summary = "A sample university",
                                            description = "This is a sample university description.",
                                            value = "{\"name\":\"Harvard University\",\"description\":\"An Ivy League university located in Cambridge, MA.\",\"courses\":[{\"id\":1,\"name\":\"Computer Science\",\"description\":\"Study of computer systems and programming.\"}]}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "University not found.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Error Response",
                                            summary = "Error response for university not found",
                                            description = "This error occurs when the university ID does not exist.",
                                            value = "{\"message\":\"University not found\"}"
                                    )
                            )
                    )
            }
    )
    @GetMapping(value = "/universities/find/{name}", produces = "application/json")
    public ResponseEntity<UniversityDto> findById(@PathVariable String name) {
        UniversityDto universityDto = universityService.getUniversityByName(name);
        if (universityDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(universityDto);
    }

    @Operation(
            summary = "Publish a new university",
            description = "Create and publish a new university.",
            requestBody = @RequestBody(
                    description = "Details of the university to be created, including name, description, and location.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UniversityDto.class),
                            examples = @ExampleObject(
                                    name = "New University Request",
                                    summary = "Example of a new university creation request",
                                    value = "{\"name\":\"Stanford University\",\"description\":\"A prestigious university in California.\"}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "University created successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UniversityDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid university data provided.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Error Response",
                                            summary = "Error response for invalid data",
                                            description = "Occurs when the provided university data is invalid or incomplete.",
                                            value = "{\"message\":\"Invalid university data\"}"
                                    )
                            )
                    )
            }
    )
    @PostMapping("/universities/publish")
    public ResponseEntity<UniversityDto> createUniversity(@RequestBody UniversityDto universityDto) {
        LOGGER.info("Creating university {}", universityDto);
        universityService.createUniversity(universityDto);
        return ResponseEntity.status(201).body(universityDto);
    }

    @Operation(
            summary = "List all universities",
            description = "Retrieve a list of all universities, including associated courses.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of universities retrieved successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = List.class)
                            )
                    )
            }
    )
    @GetMapping("/universities/all")
    public ResponseEntity<List<UniversityDto>> findAllUniversities() {
        return ResponseEntity.ok(universityService.findAllUniversities());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request, object not found.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Error Response",
                                    summary = "Error response for object not found",
                                    description = "This error occurs when the requested object does not exist.",
                                    value = "{\"message\":\"Object not found\"}"
                            )
                    )
            )
    })
    public ResponseEntity<String> handleObjectNotFoundException(ObjectNotFoundException ex) {
        LOGGER.error("ObjectNotFoundException caught: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
