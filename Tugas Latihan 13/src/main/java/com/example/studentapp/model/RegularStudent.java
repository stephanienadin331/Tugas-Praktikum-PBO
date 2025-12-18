package com.example.studentapp.model;

public class RegularStudent implements Student {
    private final String npm;
    private String name;
    private double gpa;

    public RegularStudent(String npm, String name, double gpa) {
        this.npm = npm;
        this.name = name;
        this.gpa = gpa;
    }

    @Override public String getNpm() { return npm; }
    @Override public String getName() { return name; }
    @Override public double getGpa() { return gpa; }
}