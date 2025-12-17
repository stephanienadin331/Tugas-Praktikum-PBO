import java.io.*;
import java.util.*;

public class StudentService {
    private final StudentRepository repo = new StudentRepository();

    public void add(Student s) { repo.add(s); }
    public List<Student> getAll() { return repo.getAll(); }
    public Student findByNpm(String npm) { return repo.findByNpm(npm); }
    public List<Student> findByName(String n) { return repo.findByName(n); }
    public boolean update(String npm, Student s) { return repo.update(npm, s); }
    public boolean remove(String npm) { return repo.remove(npm); }

    // ---------------- CSV ----------------
    public void exportCsv(String file) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (Student s : repo.getAll()) {
                pw.println(s.getNpm() + "," + s.getName() + "," + s.getAge());
            }
            System.out.println("Berhasil export ke " + file);
        } catch (Exception e) {
            System.out.println("Gagal export: " + e.getMessage());
        }
    }

    public void loadCsv(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                repo.add(new Student(p[0], p[1], Integer.parseInt(p[2])));
            }
            System.out.println("Berhasil load dari " + file);
        } catch (Exception e) {
            System.out.println("Gagal load: " + e.getMessage());
        }
    }
}
