package com.softuni.client.web;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    @GetMapping("/users/login")
    public String logIn(Model model){
        return "auth-login";
    }

    @GetMapping("/users/register")
    public String register(Model model){
        return "auth-register";
    }


}
