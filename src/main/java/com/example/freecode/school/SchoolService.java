package com.example.freecode.school;

import com.example.freecode.school.SchoolDto;
import com.example.freecode.school.SchoolMapper;
import com.example.freecode.school.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDto createSchool(SchoolDto dto) {
        School school = schoolMapper.toSchool(dto);
        schoolRepository.save(school);
        return dto;
    }

    public List<SchoolDto> getSchools() {
        return schoolRepository.findAll().stream().map((School s)->schoolMapper.toSchoolDto(s)).collect(Collectors.toList());
    }
}
