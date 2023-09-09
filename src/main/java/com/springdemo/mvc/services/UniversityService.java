package com.springdemo.mvc.services;

import com.springdemo.mvc.models.University;
import com.springdemo.mvc.repositories.UniversityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepo universityRepo;

//    Add a university
    public University createUniversity (University newUniversity) {
        return universityRepo.save(newUniversity);
    }

//    Get all universities
    public List<University> findAllUniversities() {
        return universityRepo.findAll();
    }

//    Get one university by id
    public University getOneUniversityById(Long id) {
        Optional<University> optionalUniversity = universityRepo.findById(id);
        if(optionalUniversity.isPresent()) {
            return optionalUniversity.get();
        }
        else {
            System.out.println("ID " + id + " not found");
            return null;
        }
    }

//    Update university
    public University updateUniversity(University updatedUniversity) {
        return universityRepo.save(updatedUniversity);
    }

//    Delete a university
    public void deleteUniversity(Long id) {
        universityRepo.deleteById(id);
    }
}
