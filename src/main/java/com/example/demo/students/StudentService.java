package com.example.demo.students;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDTO createStudent(StudentDTO dto) {
        Student student = studentMapper.toStudent(dto);
        return studentMapper.toStudentResponseDTO(studentRepository.save(student));
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return studentMapper.toStudentResponseDTO(student);
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentResponseDTO updateStudent(Long id, StudentDTO updatedStudentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        studentMapper.updateStudentFromDTO(updatedStudentDTO, student);

        return studentMapper.toStudentResponseDTO(studentRepository.save(student));
    }
}
