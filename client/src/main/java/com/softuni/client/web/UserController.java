package com.softuni.client.web;

import com.softuni.client.domain.dto.user.RegisterDto;
import com.softuni.client.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.softuni.client.utils.Messages.BINDING_RESULT_PATH;
import static com.softuni.client.utils.Messages.DOT;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String logIn(Model model) {
        return "auth-login";
    }

    @GetMapping("/users/register")
    public String register(org.springframework.ui.Model model) {
        if (!model.containsAttribute("registerDto")) {
            model.addAttribute("registerDto", new RegisterDto());
        }
        return "auth-register";
    }

    @PostMapping("/users/register")
    public ModelAndView doRegister(@Valid RegisterDto userRegisterDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            final String attributeName = "registerDto";
            redirectAttributes
                    .addFlashAttribute("registerDto", userRegisterDto)
                    .addFlashAttribute(BINDING_RESULT_PATH + DOT + attributeName, bindingResult);
            modelAndView.setViewName("redirect:/users/register");
            return modelAndView;
        }
        userService.registerUser(userRegisterDto);
        modelAndView.setViewName("redirect:/users/last-register-step");
        return modelAndView;
    }
    @GetMapping("/users/last-register-step")
    public String confirmTab(){
        return "confirm-email";
    }

    @PostMapping("/users/activate/{activation_code}")
    public String activateAccount(@PathVariable("activation_code") String activationCode,
                                        RedirectAttributes redirectAttributes){
        this.userService.activateAccount(activationCode);
        redirectAttributes.addFlashAttribute("successMessage","Successfully registered ! Please Log In !");
        return "redirect:/users/login";
    }

    @PostMapping("/users/login-error")
    public String onFailure(
            Model model) {

        model.addAttribute("badRequest", "Invalid username or password !");

        return "auth-login";
    }

}
