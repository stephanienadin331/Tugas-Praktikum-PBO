public class Student {
    private final int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student(Student other) {
        this.id = other.id;
        this.name = other.name;
        this.age = other.age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public void setName(String n) { this.name = n; }
    public void setAge(int a) { this.age = a; }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Name: %-15s | Age: %d", id, name, age);
    }
}

