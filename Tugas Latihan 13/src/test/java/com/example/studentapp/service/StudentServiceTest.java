package com.example.studentapp.service;

import com.example.studentapp.factory.StudentFactory;
import com.example.studentapp.repository.InMemoryStudentRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @Test
    void scholarshipTotals() {
        var repo = new InMemoryStudentRepository();
        var service = new StudentService(repo);
        service.createStudent("s1","A",3.2, StudentFactory.Type.SCHOLARSHIP,"25.0");
        service.createStudent("s2","B",3.6, StudentFactory.Type.SCHOLARSHIP,"50.0");
        assertEquals(75.0, service.totalScholarshipPercentageSum(), 1e-9);
        assertEquals(750.0, service.totalScholarshipForTuition(1000.0), 1e-9);
    }
}