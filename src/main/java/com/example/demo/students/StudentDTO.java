package com.example.demo.students;

public record StudentDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        Long schoolId
) {
    // Constructor (optional)
    public StudentDTO {
    }

    // Getters
    public Long getId() {
        return id;
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

    public Long getSchoolId() {
        return schoolId;
    }

}
		