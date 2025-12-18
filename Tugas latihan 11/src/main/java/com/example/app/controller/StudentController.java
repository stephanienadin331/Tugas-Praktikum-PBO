package com.example.app.controller;
import com.example.app.service.StudentService;
import com.example.app.model.Student;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService svc;
    public StudentController(StudentService svc){this.svc=svc;}

    @GetMapping public List<Student> all(){return svc.getAll();}
    @PostMapping public Student create(@RequestBody Student s){return svc.create(s.getNpm(),s.getName(),s.getGpa());}
    @GetMapping("/{npm}") public Student get(@PathVariable String npm){return svc.findByNpm(npm);}
    @DeleteMapping("/{npm}") public void del(@PathVariable String npm){svc.delete(npm);}
}
