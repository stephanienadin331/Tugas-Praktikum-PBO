package com.example.app.model;
public class Student {
    private String npm,name; private double gpa;
    public Student(){}
    public Student(String npm,String name,double gpa){this.npm=npm;this.name=name;this.gpa=gpa;}
    public String getNpm(){return npm;}
    public String getName(){return name;}
    public double getGpa(){return gpa;}
}