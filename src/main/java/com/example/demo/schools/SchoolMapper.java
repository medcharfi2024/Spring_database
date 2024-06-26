package com.example.demo.schools;

import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

	public School toSchool(SchoolDTO dto) {
        var school = new School();
        school.setName(dto.getName());
        return school;
    }

	public SchoolDTO toSchoolDTO(School school) {
        return new SchoolDTO(school.getName());
    }

    public void updateSchoolFromDTO(SchoolDTO dto, School school) {
        school.setName(dto.getName());
    }
}
