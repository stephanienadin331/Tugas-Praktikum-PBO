import java.util.*;

public class StudentRepository {

    private final List<Student> data = new ArrayList<>();

    public void add(Student s) {
        data.add(s);
    }

    public Student findByNpm(String npm) {
        return data.stream()
                .filter(s -> s.getNpm().equals(npm))
                .findFirst()
                .orElse(null);
    }

    public List<Student> findByName(String name) {
        String low = name.toLowerCase();
        return data.stream()
                .filter(s -> s.getName().toLowerCase().contains(low))
                .toList();
    }

    public boolean update(String npm, String name, double gpa) {
        Student s = findByNpm(npm);
        if (s == null) return false;
        s.setName(name);
        s.setGpa(gpa);
        return true;
    }

    public boolean delete(String npm) {
        return data.removeIf(s -> s.getNpm().equals(npm));
    }

    public List<Student> getAll() {
        return data;
    }
}
