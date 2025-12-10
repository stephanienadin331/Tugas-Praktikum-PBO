import java.util.*;

public class Inheritance {

    // ================================
    // CLASS Employee (Superclass)
    // ================================
    static abstract class Employee {
        protected String name;
        protected double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() { return name; }

        // abstract bonus method
        public abstract double calculateBonus();

        // optional: get job title
        public abstract String getRole();
    }

    // ================================
    // CLASS Manager
    // ================================
    static class Manager extends Employee {

        public Manager(String name, double salary) {
            super(name, salary);
        }

        @Override
        public double calculateBonus() {
            return salary * 0.30;   // 30%
        }

        @Override
        public String getRole() {
            return "Manager";
        }
    }

    // ================================
    // CLASS Staff
    // ================================
    static class Staff extends Employee {

        public Staff(String name, double salary) {
            super(name, salary);
        }

        @Override
        public double calculateBonus() {
            return salary * 0.10;   // 10%
        }

        @Override
        public String getRole() {
            return "Staff";
        }
    }

    // ================================
    // MAIN PROGRAM
    // ================================
    public static void main(String[] args) {

        // Daftar employee
        List<Employee> employees = new ArrayList<>();

        employees.add(new Manager("Alice", 5000));
        employees.add(new Staff("Bob", 2500));

        double totalBonus = 0;

        // Cetak bonus setiap employee
        for (Employee e : employees) {
            double bonus = e.calculateBonus();
            totalBonus += bonus;

            System.out.println(e.getName() + " (" + e.getRole() + ") - Bonus: " + bonus);
        }

        // Cetak total bonus
        System.out.println("Total Bonus: " + totalBonus);
    }
}


