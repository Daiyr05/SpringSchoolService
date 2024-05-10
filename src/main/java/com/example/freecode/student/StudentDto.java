package com.example.freecode.student;

import jakarta.validation.constraints.NotNull;

public record StudentDto(@NotNull(message = "FirstName should not be empty") String firstName,
                         @NotNull(message = "LastName should not be empty") String lastName,
                         String email,
                         Long schoolId) {
}
