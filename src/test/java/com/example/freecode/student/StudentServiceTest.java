package com.example.freecode.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @InjectMocks
    StudentService studentService;
    @Mock
    StudentRepository studentRepository;
    @Mock
    StudentMapper studentMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_save_student(){
        //Given
        StudentDto studentDto = new StudentDto("Alikan","Alikhan","Alik@gmail.com",1l);
        Student student = new Student("Alikan","Alikhan","Alik@gmail.com");
//        StudentResponseDto studentResponseDto = new StudentResponseDto("Alikan","Alikhan","Alik@gmail.com");
        //mock calls
        Mockito.when( studentMapper.toStudent(studentDto)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Mockito.when(studentMapper.toResponseStudent(student)).thenReturn(new StudentResponseDto("Alikan","Alikhan","Alik@gmail.com"));

        //When
        StudentResponseDto studentResponseDto = studentService.addStudent(studentDto);
        //Then

        assertEquals(studentDto.firstName(),student.getFirstName());
        assertEquals(studentDto.lastName(),student.getLastName());
        assertEquals(studentDto.email(),student.getEmail());
    }
}