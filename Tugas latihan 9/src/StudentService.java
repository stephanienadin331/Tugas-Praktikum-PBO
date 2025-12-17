import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class StudentService {

    private StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public void createStudent(String npm, String name, double gpa) {
        if (repo.findByNpm(npm) != null)
            throw new RuntimeException("NPM already exists!");

        repo.add(new Student(npm, name, gpa));
    }

    public boolean updateStudent(String npm, String name, double gpa) {
        return repo.update(npm, name, gpa);
    }

    public boolean deleteStudent(String npm) {
        return repo.delete(npm);
    }

    public List<Student> getAllSorted() {
        return repo.getAll().stream()
                .sorted(Comparator.comparing(Student::getNpm))
                .toList();
    }

    public List<Student> searchByName(String keyword) {
        return repo.findByName(keyword);
    }
    public List<Student> getAllSortedByNpm() {
        return repo.getAll().stream()
                .sorted(Comparator.comparing(Student::getNpm))
                .toList();
    }
    public Student searchByNpm(String npm) {
        return repo.findByNpm(npm);
    }

    public List<Student> getPage(int page, int size) {
        List<Student> list = getAllSortedByNpm();

        int start = (page - 1) * size;
        if (start >= list.size()) {
            return Collections.emptyList();
        }

        int end = Math.min(start + size, list.size());
        return list.subList(start, end);
    }

}
