package com.springdemo.mvc.controllers;

import com.springdemo.mvc.models.University;
import com.springdemo.mvc.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping("/")
    public String homeRoute() {
        return "redirect:/universities";
    }

    @GetMapping("/universities")
    public String allUniversitiesPage() {

        return "universities";
    }

    @GetMapping("/universities/new")
    public String newUniversity(Model model) {
        model.addAttribute("newUniversity", new University());
        return "newUniversity";
    }
}
