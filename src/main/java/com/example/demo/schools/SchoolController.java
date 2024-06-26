package com.example.demo.schools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public SchoolDTO createSchool(@RequestBody SchoolDTO dto) {
        return schoolService.createSchool(dto);
    }

    @GetMapping("/{id}")
    public SchoolDTO getSchoolById(@PathVariable Long id) {
        return schoolService.getSchoolById(id);
    }

    @GetMapping
    public List<SchoolDTO> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @DeleteMapping("/{id}")
    public void deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
    }

    @PutMapping("/{id}")
    public SchoolDTO updateSchool(@PathVariable Long id, @RequestBody SchoolDTO updatedSchoolDTO) {
        return schoolService.updateSchool(id, updatedSchoolDTO);
    }
}
