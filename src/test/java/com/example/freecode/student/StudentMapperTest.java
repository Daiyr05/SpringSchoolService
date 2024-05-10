package com.example.freecode.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    StudentMapper studentMapper;
    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtotoStudent(){
        StudentDto studentDto = new StudentDto("Alikan","Alikhan","Alik@gmail.com",1l);
        Student student = studentMapper.toStudent(studentDto);
        assertEquals(studentDto.firstName(),student.getFirstName());
        assertEquals(studentDto.lastName(),student.getLastName());
        assertEquals(studentDto.email(),student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.schoolId(),student.getSchool().getId());
    }
    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null(){
        var exp = assertThrows(NullPointerException.class,()->{studentMapper.toStudent(null);});
        assertEquals(exp.getMessage(),"This is null object!");
    }
    @Test
    public void shouldMapStudentToStudentDto(){
        Student student = new Student("Alikan","Alikhan","Alik@gmail.com");
        StudentResponseDto studentResponseDto = studentMapper.toResponseStudent(student);
        assertEquals(studentResponseDto.firstName(),student.getFirstName());
        assertEquals(studentResponseDto.lastName(),student.getLastName());
        assertEquals(studentResponseDto.email(),student.getEmail());
    }
}