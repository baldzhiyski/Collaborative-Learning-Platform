package com.softuni.client.web;

import com.softuni.client.domain.user_details.StudyPlatformUserDetails;
import com.softuni.client.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if(userDetails instanceof StudyPlatformUserDetails studyPlatformUserDetails){
            model.addAttribute("fullName",studyPlatformUserDetails.getFullName());
            model.addAttribute("subscribed",userService.isSubscribed(((StudyPlatformUserDetails) userDetails).getId()));
        }
        return "index";
    }

}
