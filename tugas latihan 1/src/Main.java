import java.util.*;

public class Main{

    // Class Student
    static class Student {
        private String npm;
        private String name;
        private double gpa;

        // Konstruktor
        public Student(String npm, String name, double gpa) {
            this.npm = npm;
            this.name = name;
            this.gpa = gpa;
        }

        // Getter
        public String getNpm() {
            return npm;
        }

        public String getName() {
            return name;
        }

        public double getGpa() {
            return gpa;
        }

        // Metode display()
        public void display() {
            System.out.println("Student " + npm + " - " + name + ", GPA: " + gpa);
        }
    }

    // Main method
    public static void main(String[] args) {

        Student s1 = new Student("210101", "Siti", 3.75);
        Student s2 = new Student("210102", "Agus", 3.20);
        Student s3 = new Student("210103", "Rina", 3.90);

        s1.display();
        s2.display();
        s3.display();
    }
}

