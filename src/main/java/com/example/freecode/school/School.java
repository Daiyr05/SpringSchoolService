package com.example.freecode.school;

import com.example.freecode.student.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class School {
    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(generator = "school_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @OneToMany(
            mappedBy = "school"
    )
    @JsonManagedReference
    private List<Student> studentList;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
