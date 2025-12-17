import java.util.*;

public class StudentController {
    private final StudentService service;
    private final Scanner scanner = new Scanner(System.in);

    public StudentController(StudentService service) {
        this.service = service;
    }

    public void start() {
        while (true) {
            showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": addStudent(); break;
                case "2": listStudents(); break;
                case "3": searchByNpm(); break;
                case "4": searchByName(); break;
                case "5": updateStudent(); break;
                case "6": deleteStudent(); break;
                case "7": showPage(); break;
                case "0": System.out.println("Bye"); return;
                default: System.out.println("Pilihan tidak dikenal");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Lihat Semua");
        System.out.println("3. Cari by NPM");
        System.out.println("4. Cari by Nama");
        System.out.println("5. Update");
        System.out.println("6. Hapus");
        System.out.println("7. Pagination");
        System.out.println("0. Exit");
        System.out.print("Pilih> ");
    }

    private void addStudent() {
        try {
            System.out.print("NPM: ");
            String npm = scanner.nextLine().trim();

            System.out.print("Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("GPA: ");
            double gpa = Double.parseDouble(scanner.nextLine().trim());

            service.createStudent(npm, name, gpa);
            System.out.println("Mahasiswa ditambahkan.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listStudents() {
        List<Student> list = service.getAllSortedByNpm();
        if (list.isEmpty()) System.out.println("Tidak ada mahasiswa.");
        else list.forEach(System.out::println);
    }

    private void searchByNpm() {
        System.out.print("NPM: ");
        String npm = scanner.nextLine().trim();

        Student s = service.searchByNpm(npm);
        System.out.println(s == null ? "Tidak ditemukan" : s);
    }
    private void searchByName() {
        System.out.print("Nama/substring: ");
        String p = scanner.nextLine().trim();

        List<Student> res = service.searchByName(p);
        if (res.isEmpty()) System.out.println("Tidak ada hasil.");
        else res.forEach(System.out::println);
    }

    private void updateStudent() {
        try {
            System.out.print("NPM: ");
            String npm = scanner.nextLine().trim();

            System.out.print("Nama baru: ");
            String name = scanner.nextLine().trim();

            System.out.print("GPA baru: ");
            double gpa = Double.parseDouble(scanner.nextLine().trim());

            boolean ok = service.updateStudent(npm, name, gpa);
            System.out.println(ok ? "Update berhasil." : "Tidak ditemukan.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        System.out.print("NPM: ");
        String npm = scanner.nextLine().trim();

        boolean ok = service.deleteStudent(npm);
        System.out.println(ok ? "Dihapus." : "Tidak ditemukan.");
    }

    private void showPage() {
        try {
            System.out.print("Halaman ke: ");
            int page = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Jumlah per halaman: ");
            int size = Integer.parseInt(scanner.nextLine().trim());

            List<Student> res = service.getPage(page, size);

            if (res.isEmpty())
                System.out.println("Tidak ada data pada halaman ini.");
            else
                res.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
