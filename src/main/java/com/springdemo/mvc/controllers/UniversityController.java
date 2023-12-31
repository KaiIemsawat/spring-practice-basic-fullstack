package com.springdemo.mvc.controllers;

import com.springdemo.mvc.models.University;
import com.springdemo.mvc.services.UniversityService;
import jakarta.servlet.http.HttpServletRequest;
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
        return "redirect:/universities";
    }

    @GetMapping("/universities/edit")
    public String editOneUniversityPage(@RequestParam("uid") Long theId, Model viewModel) {
        viewModel.addAttribute("thisUniversity", universityService.findOneUniversityById(theId));
        return "editUniversity";
    }

    @PutMapping("/universities/edit")
    public String editUniversity(
            @Valid @ModelAttribute("thisUniversity") University updatedUniversity,
            BindingResult result
            ) {
        System.out.println("Inside editUniversity method");

        if(result.hasErrors()) {
            return "editUniversity";
        }
        universityService.updateUniversity(updatedUniversity);
        return "redirect:/universities";
    }

    @DeleteMapping("/universities/delete/{uid}")
    public String delete(@PathVariable("uid") Long theId) {
        universityService.deleteUniversity(theId);
        return "redirect:/universities";
    }

}
