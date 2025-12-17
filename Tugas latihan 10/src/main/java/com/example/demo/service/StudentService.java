package com.example.demo.service;

import com.example.demo.dto.StudentRequest;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student create(StudentRequest dto) {

        // cek NPM duplikat
        if (repository.existsByNpm(dto.getNpm())) {
            throw new RuntimeException("NPM sudah terdaftar");
        }

        Student s = new Student();
        s.setNpm(dto.getNpm());
        s.setName(dto.getName());
        s.setIpk(dto.getIpk());

        return repository.save(s);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student tidak ditemukan"));
    }

    public List<Student> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
