public class Student {
    private String npm;
    private String name;
    private double gpa;

    public Student(String npm, String name, double gpa) {
        this.npm = npm;
        this.name = name;
        this.gpa = gpa;
    }

    public String getNpm() { return npm; }
    public String getName() { return name; }
    public double getGpa() { return gpa; }

    public void setName(String name) { this.name = name; }
    public void setGpa(double gpa) { this.gpa = gpa; }
}
