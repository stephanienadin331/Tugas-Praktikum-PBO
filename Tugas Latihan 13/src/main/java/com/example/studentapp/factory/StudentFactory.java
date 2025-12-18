package com.example.studentapp.factory;

import com.example.studentapp.model.*;

public class StudentFactory {
    public enum Type { REGULAR, EXCHANGE, SCHOLARSHIP }

    public static Student create(Type type, String npm, String name, double gpa, String extra) {
        if (type == null) throw new IllegalArgumentException("Type required");
        switch (type) {
            case REGULAR:
                return new RegularStudent(npm, name, gpa);
            case EXCHANGE:
                if (extra == null) throw new IllegalArgumentException("homeUniversity required");
                return new ExchangeStudent(npm, name, gpa, extra);
            case SCHOLARSHIP:
                if (extra == null) throw new IllegalArgumentException("scholarshipPercentage required");
                double p;
                try {
                    p = Double.parseDouble(extra);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("invalid scholarship percentage");
                }
                if (p < 0 || p > 100) throw new IllegalArgumentException("percent 0..100");
                return new ScholarshipStudent(npm, name, gpa, p);
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }
}