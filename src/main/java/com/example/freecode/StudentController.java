package com.example.freecode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/post")
    public Student addStudent(@RequestBody StudentDto studentDto){
        Student student = toStudent(studentDto);
        Optional<Student> optionalStudent = studentRepository.findByEmail(student.getEmail());
        if(optionalStudent.isPresent()){
            throw new IllegalStateException("Student is already added");
        }
       return studentRepository.save(student);
    }
    private Student toStudent(StudentDto studentDto){
        var student = new Student(studentDto.firstName(),studentDto.lastName(),studentDto.email());
        var school = new School();
        school.setId(studentDto.schoolId());
        student.setSchool(school);
        return student;
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable("id") Long id){
        boolean exitst = studentRepository.existsById(id);
        if(exitst){
            return studentRepository.findById(id).orElse(null);
        }
        throw new IllegalStateException("There is no such student");
    }

    @GetMapping("/students/search/{name}")
    public List<Student> getStudentsByName(@PathVariable("name") String name){
        return studentRepository.findAllByFirstNameContaining(name);
    }


}
