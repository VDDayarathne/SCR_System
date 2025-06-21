package com.project.scrsystem.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Registration {
    private UUID studentId;
    private UUID courseId;
    private LocalDateTime registeredAt;

    public Registration() {}

    public Registration(UUID studentId, UUID courseId, LocalDateTime registeredAt) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.registeredAt = registeredAt;
    }

    public UUID getStudentId() { return studentId; }
    public void setStudentId(UUID studentId) { this.studentId = studentId; }

    public UUID getCourseId() { return courseId; }
    public void setCourseId(UUID courseId) { this.courseId = courseId; }

    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }
}
