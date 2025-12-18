package com.example.app.service;
import com.example.app.repo.InMemoryStudentRepository;
import com.example.app.model.Student;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {
    StudentService svc;

    @BeforeEach void init(){svc=new StudentService(new InMemoryStudentRepository());}

    @Test void ok(){
        Student s=svc.create("1","A",3.0);
        assertEquals("1",s.getNpm());
    }

    @Test void dup(){
        svc.create("1","A",3.0);
        assertThrows(IllegalArgumentException.class,()->svc.create("1","B",3.2));
    }

    @Test void notfound(){
        assertThrows(Exception.class,()->svc.findByNpm("xx"));
    }
}