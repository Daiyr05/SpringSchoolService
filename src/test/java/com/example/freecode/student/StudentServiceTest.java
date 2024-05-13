package com.example.freecode.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        //check if methods called only one time
        Mockito.verify(studentMapper,Mockito.times(1)).toStudent(studentDto);
        Mockito.verify(studentRepository,Mockito.times(1)).save(student);
        Mockito.verify(studentMapper,Mockito.times(1)).toResponseStudent(student);
    }

    @Test
    public void should_return_all(){
        //Given
        List<Student> list = new ArrayList<>();
        list.add(new Student("Alikan","Alikhan","Alik@gmail.com"));

        //mock calls
        Mockito.when(studentRepository.findAll()).thenReturn(list);
        Mockito.when(studentMapper.toResponseStudent(Mockito.any(Student.class))).thenReturn(new StudentResponseDto("Alikan","Alikhan","Alik@gmail.com"));

        //When
        List<StudentResponseDto> studentResponseDtos = studentService.getStudents();
        assertEquals(studentResponseDtos.size(),list.size());

        Mockito.verify(studentRepository,Mockito.times(1)).findAll();
    }

    @Test
    public void should_return_student_by_ID(){
        //Given
        Student student1 = new Student("Alikan","Alikhan","Alik@gmail.com");
        Long id = 1l;

        //Mock
        Mockito.when(studentRepository.existsById(id)).thenReturn(true);
        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.of(student1));
        Mockito.when(studentMapper.toResponseStudent(student1)).thenReturn(new StudentResponseDto("Alikan","Alikhan","Alik@gmail.com"));

        //When
        StudentResponseDto studentResponseDto = studentService.getStudentById(id);

        //Then
        assertEquals(studentResponseDto.firstName(),"Alikan");
        assertEquals(studentResponseDto.lastName(),"Alikhan");
        assertEquals(studentResponseDto.email(),"Alik@gmail.com");
        Mockito.verify(studentRepository,Mockito.times(1)).findById(id);
    }

    @Test
    public void should_return_students_by_name(){
        //Given
        List<Student> list = new ArrayList<>();
        Student student = new Student("Alikan","Alikhan","Alik@gmail.com");
        StudentResponseDto studentResponseDto = new StudentResponseDto("Alikan","Alikhan","Alik@gmail.com");
        list.add(student);
        String name = student.getFirstName();

        //Mock calls
        Mockito.when(studentRepository.findAllByFirstNameContaining(name)).thenReturn(list);
        Mockito.when(studentMapper.toResponseStudent(student)).thenReturn(studentResponseDto);

        //When
        List<StudentResponseDto> studentResponseDtos = studentService.getStudentsByName(name);

        //Then
        assertEquals(studentResponseDtos.size(),list.size());
        assertEquals(studentResponseDtos.get(0).firstName(),list.get(0).getFirstName());

        Mockito.verify(studentRepository,Mockito.times(1)).findAllByFirstNameContaining(name);
    }


}