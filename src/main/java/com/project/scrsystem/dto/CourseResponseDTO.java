package com.project.scrsystem.dto;

import java.util.UUID;

public class CourseResponseDTO {
    private UUID id;
    private String code;
    private String title;
    private String instructor;

    public CourseResponseDTO() {}

    public CourseResponseDTO(UUID id, String code, String title, String instructor) {
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
