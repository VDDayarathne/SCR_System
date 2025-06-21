package com.project.scrsystem.controller;

import com.project.scrsystem.model.Course;
import com.project.scrsystem.model.Student;
import com.project.scrsystem.service.CourseService;
import com.project.scrsystem.service.RegistrationService;
import com.project.scrsystem.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students/{studentId}")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final StudentService studentService;
    private final CourseService courseService;

    public RegistrationController(RegistrationService registrationService,
                                  StudentService studentService,
                                  CourseService courseService) {
        this.registrationService = registrationService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @PostMapping("/register/{courseId}")
    public ResponseEntity<String> registerCourse(@PathVariable UUID studentId,
                                                 @PathVariable UUID courseId) {
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);
        registrationService.registerCourse(student, course);
        return ResponseEntity.ok("Student registered to course successfully.");
    }

    @DeleteMapping("/drop/{courseId}")
    public ResponseEntity<String> dropCourse(@PathVariable UUID studentId,
                                             @PathVariable UUID courseId) {
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);
        registrationService.dropCourse(student, course);
        return ResponseEntity.ok("Course dropped successfully.");
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getStudentCourses(@PathVariable UUID studentId) {
        Student student = studentService.getStudent(studentId);
        return ResponseEntity.ok(
                registrationService.getRegisteredCourses(student, courseService.getAllCourseMap()));
    }
}
