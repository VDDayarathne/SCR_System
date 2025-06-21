package com.project.scrsystem.service;

import com.project.scrsystem.dto.StudentRequestDTO;
import com.project.scrsystem.dto.StudentResponseDTO;
import com.project.scrsystem.exception.CustomException;
import com.project.scrsystem.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private final Map<UUID, Student> studentMap = new HashMap<>();

    public StudentResponseDTO addStudent(StudentRequestDTO dto) {
        for (Student s : studentMap.values()) {
            if (s.getEmail().equalsIgnoreCase(dto.getEmail())) {
                throw new CustomException("Email already exists", HttpStatus.CONFLICT);
            }
        }

        UUID id = UUID.randomUUID();
        Student student = new Student(id, dto.getName(), dto.getEmail());
        studentMap.put(id, student);

        return new StudentResponseDTO(id, student.getName(), student.getEmail());
    }

    public Student getStudent(UUID id) {
        Student s = studentMap.get(id);
        if (s == null) {
            throw new CustomException("Student not found", HttpStatus.NOT_FOUND);
        }
        return s;
    }

    public List<StudentResponseDTO> getAllStudents() {
        List<StudentResponseDTO> result = new ArrayList<>();
        for (Student s : studentMap.values()) {
            result.add(new StudentResponseDTO(s.getId(), s.getName(), s.getEmail()));
        }
        return result;
    }
}
