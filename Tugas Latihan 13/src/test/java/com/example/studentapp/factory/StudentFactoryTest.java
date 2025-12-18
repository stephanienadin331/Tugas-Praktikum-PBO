package com.example.studentapp.factory;

import com.example.studentapp.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentFactoryTest {
    @Test
    void createScholarship() {
        Student s = StudentFactory.create(StudentFactory.Type.SCHOLARSHIP, "S01", "X", 3.5, "50.0");
        assertTrue(s instanceof ScholarshipStudent);
        assertEquals(50.0, ((ScholarshipStudent)s).getScholarshipPercentage());
    }
}