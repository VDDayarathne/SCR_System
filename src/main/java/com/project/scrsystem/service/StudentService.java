package com.project.scrsystem.service;

import com.project.scrsystem.dto.StudentRequestDTO;
import com.project.scrsystem.dto.StudentResponseDTO;
import com.project.scrsystem.model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StudentService {
    private final Map<UUID, Student> studentMap = new HashMap<>();

    public StudentResponseDTO addStudent(StudentRequestDTO dto) {
        // email unique check
        for (Student s : studentMap.values()) {
            if (s.getEmail().equalsIgnoreCase(dto.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
        }

        UUID id = UUID.randomUUID();
        Student student = new Student(id, dto.getName(), dto.getEmail());
        studentMap.put(id, student);

        return new StudentResponseDTO(id, student.getName(), student.getEmail());
    }

    public Student getStudent(UUID id) {
        Student s = studentMap.get(id);
        if (s == null) throw new RuntimeException("Student not found");
        return s;
    }

    public Collection<Student> getAllStudents() {
        return studentMap.values();
    }


}
