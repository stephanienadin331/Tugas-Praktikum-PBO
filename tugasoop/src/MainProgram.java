import java.util.*;

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(Student s) { // defensive copy constructor
        this.id = s.id;
        this.name = s.name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public void setName(String newName) { this.name = newName; }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "'}";
    }
}

class StudentRepository {
    private Map<String, Student> data = new HashMap<>();
    private List<String> logs = new ArrayList<>();

    // Defensive copy ketika return Student keluar
    public Student getById(String id) {
        Student s = data.get(id);
        return (s == null) ? null : new Student(s);
    }

    public void add(Student s) {
        data.put(s.getId(), new Student(s)); // simpan copy
        logs.add("ADD: " + s);
    }

    public void update(String id, String newName) {
        Student s = data.get(id);
        if (s != null) {
            s.setName(newName);
            logs.add("UPDATE: " + new Student(s));
        }
    }

    public void remove(String id) {
        Student s = data.remove(id);
        if (s != null) {
            logs.add("REMOVE: " + s);
        }
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs); // defensive copy log
    }

    public void printAll() {
        System.out.println("\n==== DATA STUDENT ====");
        for (Student s : data.values()) {
            System.out.println(s);
        }
    }
}

public class MainProgram {

    private static void runBenchmark() {
        System.out.println("\n=== BENCHMARK ArrayList vs LinkedList ===");

        final int N = 1_000_000;

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        // Test GET
        long start, end;

        start = System.nanoTime();
        arrayList.get(N - 1);
        end = System.nanoTime();
        long arrayTime = end - start;

        start = System.nanoTime();
        linkedList.get(N - 1);
        end = System.nanoTime();
        long linkedTime = end - start;

        System.out.println("ArrayList get(N-1): " + arrayTime + " ns");
        System.out.println("LinkedList get(N-1): " + linkedTime + " ns");

        System.out.println("\nKESIMPULAN:");
        System.out.println("- ArrayList sangat cepat pada operasi get(index)");
        System.out.println("- LinkedList lambat karena harus traversal node satu per satu");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentRepository repo = new StudentRepository();

        int choice;
        do {
            System.out.println("\n===== MENU STUDENT REPOSITORY =====");
            System.out.println("1. Tambah Student");
            System.out.println("2. Update Student");
            System.out.println("3. Hapus Student");
            System.out.println("4. Lihat Semua Student");
            System.out.println("5. Lihat Log");
            System.out.println("6. Jalankan Benchmark");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan ID   : ");
                    String id = sc.nextLine();
                    System.out.print("Masukkan Nama : ");
                    String name = sc.nextLine();
                    repo.add(new Student(id, name));
                    break;

                case 2:
                    System.out.print("ID Student yg ingin diupdate: ");
                    String uid = sc.nextLine();
                    System.out.print("Nama baru: ");
                    String newName = sc.nextLine();
                    repo.update(uid, newName);
                    break;

                case 3:
                    System.out.print("ID Student yg ingin dihapus: ");
                    String rid = sc.nextLine();
                    repo.remove(rid);
                    break;

                case 4:
                    repo.printAll();
                    break;

                case 5:
                    System.out.println("\n=== LOG PERUBAHAN ===");
                    for (String log : repo.getLogs()) {
                        System.out.println(log);
                    }
                    break;

                case 6:
                    runBenchmark();
                    break;
            }

        } while (choice != 0);

        System.out.println("\nProgram selesai.");
    }
}
