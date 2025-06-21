package com.project.scrsystem.service;

import com.project.scrsystem.exception.CustomException;
import com.project.scrsystem.model.Course;
import com.project.scrsystem.model.Registration;
import com.project.scrsystem.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RegistrationService {
    private final List<Registration> registrations = new ArrayList<>();

    public void registerCourse(Student student, Course course) {
        for (Registration r : registrations) {
            if (r.getStudentId().equals(student.getId()) && r.getCourseId().equals(course.getId())) {
                throw new CustomException("Student already registered for this course", HttpStatus.CONFLICT);
            }
        }

        registrations.add(new Registration(student.getId(), course.getId(), LocalDateTime.now()));
    }

    public void dropCourse(Student student, Course course) {
        Registration toRemove = null;
        for (Registration r : registrations) {
            if (r.getStudentId().equals(student.getId()) && r.getCourseId().equals(course.getId())) {
                toRemove = r;
                break;
            }
        }
        if (toRemove == null) {
            throw new CustomException("Student is not registered for this course", HttpStatus.BAD_REQUEST);
        }
        registrations.remove(toRemove);
    }

    public List<Course> getRegisteredCourses(Student student, Map<UUID, Course> courseMap) {
        List<Course> result = new ArrayList<>();
        for (Registration r : registrations) {
            if (r.getStudentId().equals(student.getId())) {
                Course c = courseMap.get(r.getCourseId());
                if (c != null) result.add(c);
            }
        }
        return result;
    }
}
