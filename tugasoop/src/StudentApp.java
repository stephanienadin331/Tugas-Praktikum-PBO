import java.io.*;
import java.util.*;

public class StudentApp {

    // ============================================================
    // MODEL
    // ============================================================
    static class Student {
        private final String npm;
        private final String name;
        private final int age;

        public Student(String npm, String name, int age) {
            this.npm = npm;
            this.name = name;
            this.age = age;
        }

        public String getNpm() { return npm; }
        public String getName() { return name; }
        public int getAge()     { return age; }
    }

    // ============================================================
    // REPOSITORY
    // ============================================================
    static class StudentRepository {

        private final Map<String, Student> storage = new LinkedHashMap<>();

        public boolean exists(String npm) {
            return storage.containsKey(npm);
        }

        public void save(Student student) {
            storage.put(student.getNpm(), student);
        }

        public List<Student> getAll() {
            return new ArrayList<>(storage.values());
        }

        // EXPORT CSV
        public void saveToCsv(String path) {
            try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
                out.println("npm,name,age");

                for (Student s : storage.values()) {
                    out.println(s.getNpm() + "," + s.getName() + "," + s.getAge());
                }

                System.out.println("CSV berhasil disimpan ke: " + path);

            } catch (IOException e) {
                System.err.println("Gagal menyimpan CSV: " + e.getMessage());
            }
        }

        // IMPORT CSV
        public void loadFromCsv(String path) {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line = br.readLine(); // skip header

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");

                    if (parts.length != 3) {
                        System.err.println("Baris invalid, skip: " + line);
                        continue;
                    }

                    String npm = parts[0].trim();
                    String name = parts[1].trim();
                    int age;

                    try {
                        age = Integer.parseInt(parts[2].trim());
                    } catch (Exception e) {
                        System.err.println("Format umur salah, skip: " + line);
                        continue;
                    }

                    save(new Student(npm, name, age));
                }

                System.out.println("CSV berhasil dibaca dari: " + path);

            } catch (IOException e) {
                System.err.println("Gagal membaca CSV: " + e.getMessage());
            }
        }
    }

    // ============================================================
    // SERVICE
    // ============================================================
    static class StudentService {

        private final StudentRepository repo;

        public StudentService(StudentRepository repo) {
            this.repo = repo;
        }

        public void addStudent(String npm, String name, int age) {
            if (repo.exists(npm)) {
                throw new IllegalArgumentException("ERROR: NPM sudah terdaftar!");
            }
            repo.save(new Student(npm, name, age));
        }

        public List<Student> getPage(int page, int size) {
            List<Student> list = repo.getAll();
            int start = page * size;

            if (start >= list.size())
                return Collections.emptyList();

            int end = Math.min(start + size, list.size());
            return list.subList(start, end);
        }
    }

    // ============================================================
    // UTILS : TABEL
    // ============================================================
    static void printTable(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Tidak ada data.");
            return;
        }

        System.out.println("+------------+----------------------+--------+");
        System.out.println("|    NPM     |        NAMA         |  UMUR  |");
        System.out.println("+------------+----------------------+--------+");

        for (Student s : students) {
            System.out.printf("| %-10s | %-20s | %6d |\n",
                    s.getNpm(),
                    s.getName(),
                    s.getAge());
        }

        System.out.println("+------------+----------------------+--------+");
    }

    // ============================================================
    // MAIN (SCANNER MENU)
    // ============================================================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentRepository repo = new StudentRepository();
        StudentService service = new StudentService(repo);

        int menu;

        do {
            System.out.println("\n========= MENU MAHASISWA =========");
            System.out.println("1. Tambah Student");
            System.out.println("2. Lihat Semua Student");
            System.out.println("3. Export ke CSV");
            System.out.println("4. Import dari CSV");
            System.out.println("5. Pagination");
            System.out.println("0. Keluar");
            System.out.println("======================================");
            System.out.print("Pilih menu: ");
            menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {

                case 1 -> {
                    System.out.print("Masukkan NPM  : ");
                    String npm = sc.nextLine();

                    System.out.print("Masukkan Nama : ");
                    String name = sc.nextLine();

                    System.out.print("Masukkan Umur : ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    try {
                        service.addStudent(npm, name, age);
                        System.out.println("Student berhasil ditambahkan!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 2 -> {
                    System.out.println("\n===== DAFTAR STUDENT =====");
                    printTable(repo.getAll());
                }

                case 3 -> {
                    System.out.print("Masukkan path CSV: ");
                    String path = sc.nextLine();
                    repo.saveToCsv(path);
                }

                case 4 -> {
                    System.out.print("Masukkan path CSV: ");
                    String path = sc.nextLine();
                    repo.loadFromCsv(path);
                }

                case 5 -> {
                    System.out.print("Masukkan halaman (mulai 0): ");
                    int page = sc.nextInt();

                    System.out.print("Jumlah per halaman: ");
                    int size = sc.nextInt();
                    sc.nextLine();

                    List<Student> data = service.getPage(page, size);

                    System.out.println("\n===== HALAMAN " + page + " =====");
                    printTable(data);
                }

                case 0 -> System.out.println("Program selesai.");

                default -> System.out.println("Pilihan tidak valid!");
            }

        } while (menu != 0);

        sc.close();
    }
}
