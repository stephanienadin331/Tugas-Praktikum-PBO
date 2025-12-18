package com.example.studentapp.model;

public class ExchangeStudent implements Student {
    private final String npm;
    private String name;
    private double gpa;
    private final String homeUniversity;

    public ExchangeStudent(String npm, String name, double gpa, String homeUniversity) {
        this.npm = npm;
        this.name = name;
        this.gpa = gpa;
        this.homeUniversity = homeUniversity;
    }

    @Override public String getNpm() { return npm; }
    @Override public String getName() { return name; }
    @Override public double getGpa() { return gpa; }
    public String getHomeUniversity() { return homeUniversity; }
}