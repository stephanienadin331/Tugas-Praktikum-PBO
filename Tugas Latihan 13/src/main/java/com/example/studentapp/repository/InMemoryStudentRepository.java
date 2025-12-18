package com.example.studentapp.repository;

import com.example.studentapp.model.Student;
import java.util.*;

public class InMemoryStudentRepository implements StudentRepository {
    private final Map<String, Student> store = new LinkedHashMap<>();

    @Override public void save(Student student) { store.put(student.getNpm(), student); }
    @Override public boolean existsByNpm(String npm) { return store.containsKey(npm); }
    @Override public Optional<Student> findByNpm(String npm) { return Optional.ofNullable(store.get(npm)); }
    @Override public List<Student> findAll() { return new ArrayList<>(store.values()); }
    @Override public void clear() { store.clear(); }
}