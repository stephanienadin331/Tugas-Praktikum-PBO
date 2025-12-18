package com.example.app.service;
import com.example.app.model.Student;
import com.example.app.repo.StudentRepository;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository repo;
    public StudentService(StudentRepository repo){this.repo=repo;}

    public Student create(String npm,String name,double gpa){
        if(npm==null||npm.isBlank()) throw new IllegalArgumentException("NPM required");
        if(repo.existsByNpm(npm)) throw new IllegalArgumentException("NPM exists");
        Student s=new Student(npm,name,gpa);
        repo.save(s);
        return s;
    }
    public Student findByNpm(String npm){
        return repo.findByNpm(npm).orElseThrow(()->new NoSuchElementException("Not found"));
    }
    public List<Student> getAll(){return repo.getAll();}
    public void delete(String npm){repo.delete(npm);}
}