import java.util.*;

public class StudentRepository {

    private final Map<Integer, Student> students = new HashMap<>();
    private final List<ChangeLog> logs = new ArrayList<>();

    public void add(Student s) {
        students.put(s.getId(), s);
    }

    public void update(int id, String newName, int newAge) {
        Student s = students.get(id);
        if (s != null) {
            s.setName(newName);
            s.setAge(newAge);
            logs.add(new ChangeLog("UPDATE", s));
        } else {
            System.out.println(Color.RED + "Student tidak ditemukan!" + Color.RESET);
        }
    }

    public void remove(int id) {
        Student removed = students.remove(id);
        if (removed != null) {
            logs.add(new ChangeLog("REMOVE", removed));
        } else {
            System.out.println(Color.RED + "Student tidak ditemukan!" + Color.RESET);
        }
    }

    public void listStudents() {
        System.out.println(Color.CYAN + "\n╔════════════════════════ LIST STUDENT ════════════════════════╗" + Color.RESET);
        if (students.isEmpty()) {
            System.out.println(Color.YELLOW + "Tidak ada student." + Color.RESET);
        } else {
            int no = 1;
            for (Student s : students.values()) {
                System.out.println("  ID   : " + s.getId());
                System.out.println("  Name : " + s.getName());
                System.out.println("  Age  : " + s.getAge());
                System.out.println();
            }
        }
        System.out.println(Color.CYAN + "╚══════════════════════════════════════════════════════════════╝\n" + Color.RESET);
    }

    public void showLogs() {
        System.out.println(Color.PURPLE + "\n╔═════════════════════════════════════ LOG PERUBAHAN ════════════════════════════════════════╗" + Color.RESET);
        if (logs.isEmpty()) {
            System.out.println(Color.YELLOW + "Belum ada log." + Color.RESET);
        } else {
            for (ChangeLog log : logs) System.out.println("  " + log);
        }
        System.out.println(Color.PURPLE + "╚═══════════════════════════════════════════════════════════════════════════════════════════╝\n" + Color.RESET);
    }
}

