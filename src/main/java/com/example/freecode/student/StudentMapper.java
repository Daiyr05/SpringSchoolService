package com.example.freecode.student;

import com.example.freecode.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student toStudent(StudentDto studentDto){
        if (studentDto==null){
            throw new NullPointerException("This is null object!");
        }
        var student = new Student(studentDto.firstName(),studentDto.lastName(),studentDto.email());
        var school = new School();
        school.setId(studentDto.schoolId());
        student.setSchool(school);
        return student;
    }

    public StudentResponseDto toResponseStudent(Student student){
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }
}
