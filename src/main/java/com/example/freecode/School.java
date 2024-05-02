package com.example.freecode;

import jakarta.persistence.*;

@Entity
@Table
public class School {
    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(generator = "school_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    public School() {
    }

    public School(String name) {
        this.name = name;
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
