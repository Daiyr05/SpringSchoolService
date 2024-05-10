package com.example.freecode.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/post")
    public StudentResponseDto addStudent(@Valid @RequestBody StudentDto studentDto){
        return studentService.addStudent(studentDto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping("/students/{id}")
    public StudentResponseDto getStudentById(@PathVariable("id") Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/students/search/{name}")
    public List<StudentResponseDto> getStudentsByName(@PathVariable("name") String name){
        return studentService.getStudentsByName(name);
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable("id") Long id){
        studentService.delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exp){
            var errors = new HashMap<String,String>();
        exp.getBindingResult().getAllErrors().forEach((error)->{
                var fieldName = ((FieldError)error).getField();
                var errorMessage = error.getDefaultMessage();
                errors.put(fieldName,errorMessage);
            });
        return  new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
