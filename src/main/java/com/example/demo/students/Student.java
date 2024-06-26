package com.example.demo.students;

import com.example.demo.schools.School;
import com.example.demo.studentprofiles.StudentProfile;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age ;

   
	@ManyToOne
    @JsonBackReference
    private School school;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private StudentProfile profile;

    
    public Student() {}
    
    public Student(Long id, String firstName, String lastName, String email, int age, School school,
			StudentProfile profile) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.school = school;
		this.profile = profile;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public StudentProfile getProfile() {
        return profile;
    }

    public void setProfile(StudentProfile profile) {
        this.profile = profile;
    }
    
}
