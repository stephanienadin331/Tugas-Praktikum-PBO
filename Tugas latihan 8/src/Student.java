public class Student {
    private String npm;
    private String name;
    private int age;

    public Student(String npm, String name, int age) {
        this.npm = npm;
        this.name = name;
        this.age = age;
    }

    public String getNpm() { return npm; }
    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return String.format("%s - %s (%d tahun)", npm, name, age);
    }
}
