package com.project.scrsystem.Service;

import com.project.scrsystem.exception.CustomException;
import com.project.scrsystem.model.Course;
import com.project.scrsystem.model.Student;
import com.project.scrsystem.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegistrationServiceTest {

    private RegistrationService registrationService;
    private Student student;
    private Course course;

    @BeforeEach
    void setup() {
        registrationService = new RegistrationService();

        student = new Student(UUID.randomUUID(), "Kasun", "kasun@example.com");
        course = new Course(UUID.randomUUID(), "CS101", "Java", "Dr. Silva");
    }

    @Test
    void testRegisterCourse_success() {
        registrationService.registerCourse(student, course);

        List<Course> courses = registrationService.getRegisteredCourses(student, Map.of(course.getId(), course));
        assertEquals(1, courses.size());
        assertEquals("CS101", courses.get(0).getCode());
    }

    @Test
    void testRegisterCourse_duplicate() {
        registrationService.registerCourse(student, course);

        CustomException ex = assertThrows(CustomException.class, () -> {
            registrationService.registerCourse(student, course);
        });

        assertEquals("Student already registered for this course", ex.getMessage());
        assertEquals(HttpStatus.CONFLICT, ex.getStatus());
    }

    @Test
    void testDropCourse_success() {
        registrationService.registerCourse(student, course);
        registrationService.dropCourse(student, course);

        List<Course> courses = registrationService.getRegisteredCourses(student, Map.of(course.getId(), course));
        assertEquals(0, courses.size());
    }

    @Test
    void testDropCourse_notRegistered() {
        CustomException ex = assertThrows(CustomException.class, () -> {
            registrationService.dropCourse(student, course);
        });

        assertEquals("Student is not registered for this course", ex.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
    }
}
