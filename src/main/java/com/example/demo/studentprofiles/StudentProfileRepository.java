package com.example.demo.studentprofiles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
}
