package com.example.studentapp.service;

import com.example.studentapp.factory.StudentFactory;
import com.example.studentapp.model.*;
import com.example.studentapp.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student createStudent(String npm, String name, double gpa, StudentFactory.Type type, String extra) {
        if (npm == null || npm.isBlank()) throw new IllegalArgumentException("NPM required");
        if (repo.existsByNpm(npm)) throw new IllegalArgumentException("NPM exists");
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("GPA invalid");
        Student s = StudentFactory.create(type, npm, name, gpa, extra);
        repo.save(s);
        return s;
    }

    public List<Student> getAllStudents() { return repo.findAll(); }

    public double totalScholarshipPercentageSum() {
        return repo.findAll().stream()
                .filter(s -> s instanceof ScholarshipStudent)
                .mapToDouble(s -> ((ScholarshipStudent) s).getScholarshipPercentage())
                .sum();
    }

    public double totalScholarshipForTuition(double tuitionPerStudent) {
        return repo.findAll().stream()
                .filter(s -> s instanceof ScholarshipStudent)
                .mapToDouble(s -> ((ScholarshipStudent) s).getScholarshipPercentage() / 100.0 * tuitionPerStudent)
                .sum();
    }

    public List<ScholarshipStudent> findScholarshipStudents() {
        return repo.findAll().stream()
                .filter(s -> s instanceof ScholarshipStudent)
                .map(s -> (ScholarshipStudent) s)
                .collect(Collectors.toList());
    }
}