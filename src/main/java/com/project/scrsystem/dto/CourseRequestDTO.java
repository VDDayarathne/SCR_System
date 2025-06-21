package com.project.scrsystem.dto;

import jakarta.validation.constraints.NotBlank;

public class CourseRequestDTO {
    @NotBlank(message = "Course code is required")
    private String code;

    @NotBlank(message = "Course title is required")
    private String title;

    @NotBlank(message = "Instructor is required")
    private String instructor;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
}
