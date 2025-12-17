import java.util.*;

public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public void add(Student s) { students.add(s); }

    public Student findByNpm(String npm) {
        return students.stream()
                .filter(s -> s.getNpm().equals(npm))
                .findFirst()
                .orElse(null);
    }

    public List<Student> findByName(String partial) {
        String p = partial.toLowerCase();
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(p))
                result.add(s);
        }
        return result;
    }

    public boolean update(String npm, Student newData) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getNpm().equals(npm)) {
                students.set(i, newData);
                return true;
            }
        }
        return false;
    }

    public boolean remove(String npm) {
        return students.removeIf(s -> s.getNpm().equals(npm));
    }

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }
}
