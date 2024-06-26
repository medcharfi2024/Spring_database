package com.example.demo.studentprofiles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.students.Student;
import com.example.demo.students.StudentRepository;

@Controller
@RestController
@RequestMapping("/profiles")
public class StudentProfileController {

    private final StudentProfileRepository profileRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentProfileController(StudentProfileRepository profileRepository, StudentRepository studentRepository) {
        this.profileRepository = profileRepository;
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public StudentProfile createProfile(@RequestBody StudentProfile profile) {
        return profileRepository.save(profile);
    }

    @GetMapping("/{id}")
    public StudentProfile getProfileById(@PathVariable Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
    }

    @GetMapping
    public List<StudentProfile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public StudentProfile updateProfile(@PathVariable Long id, @RequestBody StudentProfile updatedProfile) {
        return profileRepository.findById(id)
                .map(profile -> {
                    profile.setBio(updatedProfile.getBio());
                    return profileRepository.save(profile);
                })
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
    }

    @PostMapping("/students/{studentId}")
    public StudentProfile createProfileForStudent(@PathVariable Long studentId, @RequestBody StudentProfile profile) {
        return studentRepository.findById(studentId)
                .map(student -> {
                    profile.setStudent(student);
                    student.setProfile(profile);
                    studentRepository.save(student);
                    return profileRepository.save(profile);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
    }

    @GetMapping("/students/{studentId}")
    public StudentProfile getProfileByStudentId(@PathVariable Long studentId) {
        return studentRepository.findById(studentId)
                .map(Student::getProfile)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
    }
}
