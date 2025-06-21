package com.project.scrsystem.controller;

import com.project.scrsystem.dto.StudentRequestDTO;
import com.project.scrsystem.dto.StudentResponseDTO;
import com.project.scrsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO requestDTO) {
        return ResponseEntity.ok(studentService.addStudent(requestDTO));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(studentService.getStudent(studentId));
    }

}
