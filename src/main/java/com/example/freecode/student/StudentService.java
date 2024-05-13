package com.example.freecode.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto addStudent(StudentDto studentDto){
        Student student = studentMapper.toStudent(studentDto);
        Optional<Student> optionalStudent = studentRepository.findByEmail(student.getEmail());
        if(optionalStudent.isPresent()){
            throw new IllegalStateException("Student is already added");
        }
        var savedStudent =  studentRepository.save(student);
        return studentMapper.toResponseStudent(savedStudent);
    }

    public StudentResponseDto getStudentById(Long id){
        boolean exitst = studentRepository.existsById(id);
        if(exitst){
            return studentRepository.findById(id).map(studentMapper::toResponseStudent).orElse(null);
        }
        throw new IllegalStateException("There is no such student");
    }

    public List<StudentResponseDto> getStudents(){
        return studentRepository.findAll().stream().map((Student student)->studentMapper.toResponseStudent(student)).collect(Collectors.toList());
    }


    public List<StudentResponseDto> getStudentsByName(String name){
        return studentRepository.findAllByFirstNameContaining(name).stream().map(studentMapper::toResponseStudent).collect(Collectors.toList());
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
