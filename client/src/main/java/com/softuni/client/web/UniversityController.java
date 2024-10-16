package com.softuni.client.web;

import com.softuni.client.domain.dto.course.CourseDto;
import com.softuni.client.domain.dto.resource.DisplayResourceDto;
import com.softuni.client.domain.dto.university.UniversityDto;

import com.softuni.client.domain.entity.University;
import com.softuni.client.exceptions.ObjectNotFoundException;
import com.softuni.client.service.UniService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

import static com.softuni.client.utils.Messages.INVALID_UNI_NAME;

@Controller
public class UniversityController {
    private UniService uniService;

    public UniversityController(UniService uniService) {
        this.uniService = uniService;
    }


    @GetMapping("/universities/details/{universityName}")
    public String getUniversityDetailsPage(Model model, @PathVariable String universityName){
        if (!model.containsAttribute("searchedUniversity")) {
            // Fetch the university again if it's not in the model
            University university = uniService.getUniByName(universityName)
                    .orElseThrow(() -> new ObjectNotFoundException(INVALID_UNI_NAME));

            UniversityDto dto = University.toDto(university);


            // Add the university DTO to the model
            model.addAttribute("searchedUniversity",dto);
            model.addAttribute("coursesDtos",dto.getCourses());
        }
        return "university-details";
    }

    @GetMapping("/universities/findByName")
    public String findUni(@RequestParam String universityName,Model model){
        return "redirect:/universities/details/" + universityName;

    }

    @GetMapping("/universities/explore-course/{courseUuid}")
    public String exploreCourseDetails(Model model, @PathVariable UUID courseUuid, Authentication authenticationPrincipal){
        List<DisplayResourceDto> materialsForCourse = this.uniService.getMaterialsForCourse(courseUuid);
        CourseDto course = this.uniService.getCourse(courseUuid);

        if(!model.containsAttribute("materials")){
            model.addAttribute("materials",materialsForCourse);
        }
        model.addAttribute("courseName",course.getName());
        model.addAttribute("courseDesc",course.getDescription());
        model.addAttribute("courseUuid", courseUuid);
        model.addAttribute("loggedInUser", authenticationPrincipal.getName());
        return "course-details";
    }
}
