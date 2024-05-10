package com.example.freecode.studentprofile;

import com.example.freecode.student.Student;
import jakarta.persistence.*;

@Entity
@Table
public class StudentProfile {
    @Id
    @SequenceGenerator(name = "profile_sequence", sequenceName = "profile_sequence", allocationSize = 1)
    @GeneratedValue(generator = "profile_sequence", strategy = GenerationType.SEQUENCE)
    private long id;
    private String bio;
    @OneToOne
    @JoinColumn(
            name = "student_id"
    )
    private Student student;
    public StudentProfile() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentProfile(String bio) {
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
