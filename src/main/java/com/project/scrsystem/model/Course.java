package com.project.scrsystem.model;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class Course {
    private UUID id;

    @NotBlank(message = "Course code is required")
    private String code;

    @NotBlank(message = "Course title is required")
    private String title;

    @NotBlank(message = "Instructor name is required")
    private String instructor;

    public Course() {}

    public Course(UUID id, String code, String title, String instructor) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.instructor = instructor;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
}
