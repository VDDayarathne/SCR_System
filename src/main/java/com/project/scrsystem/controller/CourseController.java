package com.project.scrsystem.controller;

import com.project.scrsystem.dto.CourseRequestDTO;
import com.project.scrsystem.dto.CourseResponseDTO;
import com.project.scrsystem.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> addCourse(@Valid @RequestBody CourseRequestDTO requestDTO) {
        return ResponseEntity.ok(courseService.addCourse(requestDTO));
    }

//    @GetMapping
//    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
//        return ResponseEntity.ok(courseService.getAllCourses());
//    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<CourseResponseDTO> allCourses = courseService.getAllCourses();
        int from = Math.min(page * size, allCourses.size());
        int to = Math.min(from + size, allCourses.size());
        List<CourseResponseDTO> paged = allCourses.subList(from, to);

        return ResponseEntity.ok(paged);
    }

}