package com.example.demo.students;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentController(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @PostMapping("/students")
    public StudentResponseDTO createStudent(@RequestBody StudentDTO dto) {
        var student = studentMapper.toStudent(dto);
        return studentMapper.toStudentResponseDTO(studentRepository.save(student));
    }

    @GetMapping("/students/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toStudentResponseDTO)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @GetMapping("/students")
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

    @PutMapping("/students/{id}")
    public StudentResponseDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO updatedStudentDTO) {
        return studentRepository.findById(id)
                .map(student -> {
                    studentMapper.updateStudentFromDTO(updatedStudentDTO, student);
                    return studentMapper.toStudentResponseDTO(studentRepository.save(student));
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
}
