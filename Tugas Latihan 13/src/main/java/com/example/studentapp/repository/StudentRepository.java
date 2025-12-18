package com.example.studentapp.repository;

import com.example.studentapp.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void save(Student student);
    boolean existsByNpm(String npm);
    Optional<Student> findByNpm(String npm);
    List<Student> findAll();
    void clear();
}