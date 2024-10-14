package com.softuni.client.web;

import com.softuni.client.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleNotFound(ObjectNotFoundException exception, HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value()); // Set the response status to 404
        ModelAndView modelAndView = new ModelAndView("error/404");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

}