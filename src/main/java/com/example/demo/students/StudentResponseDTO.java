package com.example.demo.students;

public record StudentResponseDTO(String firstName, String lastName, String email) {

    public StudentResponseDTO {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
