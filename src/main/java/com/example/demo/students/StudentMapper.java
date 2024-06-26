package com.example.demo.students;

import org.springframework.stereotype.Service;
import com.example.demo.schools.School;

@Service
public class StudentMapper {

    public Student toStudent(StudentDTO dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName()); // Using record getter method
        student.setLastName(dto.getLastName()); // Using record getter method
        student.setEmail(dto.getEmail()); // Using record getter method

        // Assuming you have a School entity and need to set it based on dto.schoolId
        School school = new School();
        school.setId(dto.getSchoolId()); // Using record getter method
        student.setSchool(school);

        return student;
    }

    public StudentDTO toStudentDTO(Student student) {
        return new StudentDTO(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getSchool().getId()
            // You can map other fields as needed
        );
    }

    public void updateStudentFromDTO(StudentDTO dto, Student student) {
        student.setFirstName(dto.getFirstName()); // Using record getter method
        student.setLastName(dto.getLastName()); // Using record getter method
        student.setEmail(dto.getEmail()); // Using record getter method

        // Assuming you have a School entity and need to update it based on dto.schoolId
        School school = new School();
        school.setId(dto.getSchoolId()); // Using record getter method
        student.setSchool(school);
    }

    public StudentResponseDTO toStudentResponseDTO(Student student) {
        return new StudentResponseDTO(
            student.getFirstName(),
            student.getLastName(),
            student.getEmail()
            // You can map other fields as needed
        );
    }
}
