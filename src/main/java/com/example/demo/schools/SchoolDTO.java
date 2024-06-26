package com.example.demo.schools;

public record SchoolDTO(String name) {

    public SchoolDTO {
    }

    public String getName() {
        return name;
    }
}
