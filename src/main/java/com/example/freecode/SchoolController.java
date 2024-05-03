package com.example.freecode;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/schools")
    public School createSchool(@RequestBody School school){
        return schoolRepository.save(school);
    }

    @GetMapping("/schools")
    public List<School> getSchools(){
        return schoolRepository.findAll();
    }
}
