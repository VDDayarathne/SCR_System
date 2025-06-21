package com.project.scrsystem.Service;

import com.project.scrsystem.dto.CourseRequestDTO;
import com.project.scrsystem.dto.CourseResponseDTO;
import com.project.scrsystem.exception.CustomException;
import com.project.scrsystem.model.Course;
import com.project.scrsystem.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    private CourseService courseService;

    @BeforeEach
    void setup() {
        courseService = new CourseService();
    }

    @Test
    void testAddCourse_success() {
        CourseRequestDTO dto = new CourseRequestDTO();
        dto.setCode("CS101");
        dto.setTitle("Intro to Java");
        dto.setInstructor("Dr. Silva");

        CourseResponseDTO response = courseService.addCourse(dto);

        assertNotNull(response.getId());
        assertEquals("CS101", response.getCode());
    }

    @Test
    void testAddCourse_duplicateCode() {
        CourseRequestDTO dto = new CourseRequestDTO();
        dto.setCode("CS101");
        dto.setTitle("Java");
        dto.setInstructor("Dr. A");

        courseService.addCourse(dto);

        CourseRequestDTO duplicate = new CourseRequestDTO();
        duplicate.setCode("CS101"); // same code
        duplicate.setTitle("Something else");
        duplicate.setInstructor("Dr. B");

        CustomException ex = assertThrows(CustomException.class, () -> {
            courseService.addCourse(duplicate);
        });

        assertEquals("Course code already exists", ex.getMessage());
        assertEquals(HttpStatus.CONFLICT, ex.getStatus());
    }

    @Test
    void testGetCourse_success() {
        CourseRequestDTO dto = new CourseRequestDTO();
        dto.setCode("MATH101");
        dto.setTitle("Maths");
        dto.setInstructor("Prof. Perera");

        CourseResponseDTO response = courseService.addCourse(dto);
        Course course = courseService.getCourse(response.getId());

        assertNotNull(course);
        assertEquals("MATH101", course.getCode());
    }

    @Test
    void testGetCourse_notFound() {
        UUID randomId = UUID.randomUUID();

        CustomException ex = assertThrows(CustomException.class, () -> {
            courseService.getCourse(randomId);
        });

        assertEquals("Course not found", ex.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }

    @Test
    void testGetAllCourses() {
        CourseRequestDTO dto1 = new CourseRequestDTO();
        dto1.setCode("CS1");
        dto1.setTitle("Java");
        dto1.setInstructor("A");

        CourseRequestDTO dto2 = new CourseRequestDTO();
        dto2.setCode("CS2");
        dto2.setTitle("Spring");
        dto2.setInstructor("B");

        courseService.addCourse(dto1);
        courseService.addCourse(dto2);

        List<CourseResponseDTO> list = courseService.getAllCourses();
        assertEquals(2, list.size());
    }

}
