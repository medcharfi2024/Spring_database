package com.example.demo.schools;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDTO createSchool(SchoolDTO dto) {
        School school = schoolMapper.toSchool(dto);
        return schoolMapper.toSchoolDTO(schoolRepository.save(school));
    }

    public SchoolDTO getSchoolById(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found with id: " + id));
        return schoolMapper.toSchoolDTO(school);
    }

    public List<SchoolDTO> getAllSchools() {
        List<School> schools = schoolRepository.findAll();
        return schools.stream()
                .map(schoolMapper::toSchoolDTO)
                .collect(Collectors.toList());
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }

    public SchoolDTO updateSchool(Long id, SchoolDTO updatedSchoolDTO) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found with id: " + id));

        schoolMapper.updateSchoolFromDTO(updatedSchoolDTO, school);

        return schoolMapper.toSchoolDTO(schoolRepository.save(school));
    }
}
