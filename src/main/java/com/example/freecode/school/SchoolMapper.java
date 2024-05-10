package com.example.freecode.school;

import com.example.freecode.school.SchoolDto;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public School toSchool(SchoolDto dto){
        return new School(dto.name());
    }

    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName());
    }
}
