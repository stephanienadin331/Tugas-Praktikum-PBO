package com.example.app.repo;
import com.example.app.model.Student;
import java.util.*;
public interface StudentRepository {
    boolean existsByNpm(String npm);
    void save(Student s);
    Optional<Student> findByNpm(String npm);
    List<Student> getAll();
    void delete(String npm);
}