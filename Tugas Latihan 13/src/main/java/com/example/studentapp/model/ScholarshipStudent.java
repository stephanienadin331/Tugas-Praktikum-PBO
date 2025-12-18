package com.example.studentapp.model;

public class ScholarshipStudent implements Student {
    private final String npm;
    private String name;
    private double gpa;
    private final double scholarshipPercentage;

    public ScholarshipStudent(String npm, String name, double gpa, double scholarshipPercentage) {
        this.npm = npm;
        this.name = name;
        this.gpa = gpa;
        this.scholarshipPercentage = scholarshipPercentage;
    }

    @Override public String getNpm() { return npm; }
    @Override public String getName() { return name; }
    @Override public double getGpa() { return gpa; }
    public double getScholarshipPercentage() { return scholarshipPercentage; }
}