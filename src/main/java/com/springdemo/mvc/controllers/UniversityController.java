package com.springdemo.mvc.controllers;

import com.springdemo.mvc.models.University;
import com.springdemo.mvc.services.UniversityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping("/")
    public String homeRoute() {
        return "redirect:/universities";
    }

    @GetMapping("/universities")
    public String allUniversitiesPage(Model viewModel) {
        viewModel.addAttribute("universities", universityService.findAllUniversities());
        return "universities";
    }

    @GetMapping("/universities/{id}")
    public String oneUniversityPage(Model viewModel, @PathVariable("id") Long id) {
        viewModel.addAttribute("thisUniversity", universityService.findOneUniversityById(id));
        return "oneUniversity";
    }

    @GetMapping("/universities/new")
    public String newUniversity(@ModelAttribute("newUniversity") University thisUniversity) {
        return "newUniversity";
    }

    @PostMapping("/universities/new")
    public String addNewUniversity(
            @Valid @ModelAttribute("newUniversity") University aNewUniversity,
            BindingResult result) {
        if(result.hasErrors()) {
            return "newUniversity";
        }
        universityService.createUniversity(aNewUniversity);
        return "redirect:/";
    }
}
