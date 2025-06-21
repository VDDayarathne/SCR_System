package com.project.scrsystem.Service;

import com.project.scrsystem.dto.StudentRequestDTO;
import com.project.scrsystem.dto.StudentResponseDTO;
import com.project.scrsystem.exception.CustomException;
import com.project.scrsystem.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {
    private StudentService studentService;

    @BeforeEach
    void setup() {
        studentService = new StudentService();
    }

    @Test
    void testAddStudent_success() {
        StudentRequestDTO dto = new StudentRequestDTO();
        dto.setName("Test1");
        dto.setEmail("test1@gmail.com");

        StudentResponseDTO response = studentService.addStudent(dto);

        assertNotNull(response.getId());
        assertEquals("Test1", response.getName());
        assertEquals("test1@gmail.com", response.getEmail());
    }

    @Test
    void testAddStudent_duplicateEmail() {
        StudentRequestDTO first = new StudentRequestDTO();
        first.setName("test");
        first.setEmail("test@gmail.com");
        studentService.addStudent(first);

        StudentRequestDTO duplicate = new StudentRequestDTO();
        duplicate.setName("testagain");
        duplicate.setEmail("test@gmail.com");

        CustomException ex = assertThrows(CustomException.class, () -> {
            studentService.addStudent(duplicate);
        });

        assertEquals("Email already exists", ex.getMessage());
        assertEquals(HttpStatus.CONFLICT, ex.getStatus());
    }

    @Test
    void testGetStudent_success() {
        StudentRequestDTO dto = new StudentRequestDTO();
        dto.setName("Amal");
        dto.setEmail("amal@example.com");

        StudentResponseDTO response = studentService.addStudent(dto);
        UUID id = response.getId();

        assertNotNull(studentService.getStudent(id));
    }

    @Test
    void testGetStudent_notFound() {
        UUID fakeId = UUID.randomUUID();

        CustomException ex = assertThrows(CustomException.class, () -> {
            studentService.getStudent(fakeId);
        });

        assertEquals("Student not found", ex.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }
}
