package com.project.scrsystem.service;

import com.project.scrsystem.dto.CourseRequestDTO;
import com.project.scrsystem.dto.CourseResponseDTO;
import com.project.scrsystem.exception.CustomException;
import com.project.scrsystem.model.Course;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    private final Map<UUID, Course> courseMap = new HashMap<>();

    public CourseResponseDTO addCourse(CourseRequestDTO dto) {
        for (Course c : courseMap.values()) {
            if (c.getCode().equalsIgnoreCase(dto.getCode())) {
                throw new CustomException("Course code already exists", HttpStatus.CONFLICT);
            }
        }

        UUID id = UUID.randomUUID();
        Course course = new Course(id, dto.getCode(), dto.getTitle(), dto.getInstructor());
        courseMap.put(id, course);

        return new CourseResponseDTO(id, course.getCode(), course.getTitle(), course.getInstructor());
    }

    public Course getCourse(UUID id) {
        Course c = courseMap.get(id);
        if (c == null) throw new CustomException("Course not found", HttpStatus.NOT_FOUND);
        return c;
    }

    public List<CourseResponseDTO> getAllCourses() {
        List<CourseResponseDTO> result = new ArrayList<>();
        for (Course c : courseMap.values()) {
            result.add(new CourseResponseDTO(c.getId(), c.getCode(), c.getTitle(), c.getInstructor()));
        }
        return result;
    }

    public Map<UUID, Course> getAllCourseMap() {
        return courseMap;
    }

}
