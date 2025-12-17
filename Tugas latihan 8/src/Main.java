import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        StudentService service = new StudentService();
        int choice;

        while (true) {
            System.out.println("\n=====================================");
            System.out.println("            MENU MAHASISWA           ");
            System.out.println("=====================================");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Lihat Semua");
            System.out.println("3. Cari (NPM)");
            System.out.println("4. Cari (Nama)");
            System.out.println("5. Update");
            System.out.println("6. Hapus");
            System.out.println("7. Pagination");
            System.out.println("8. Export CSV");
            System.out.println("9. Load CSV");
            System.out.println("10. Exit");
            System.out.println("=====================================");
            System.out.print("Pilih > ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> tambah(service, sc);
                case 2 -> lihatSemua(service);
                case 3 -> cariNpm(service);
                case 4 -> cariNama(service);
                case 5 -> update(service);
                case 6 -> hapus(service);
                case 7 -> pagination(service);
                case 8 -> service.exportCsv("data.csv");
                case 9 -> service.loadCsv("data.csv");
                case 10 -> {
                    System.out.println("Keluar...");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // ===================== METHOD MENU =========================

    private static void tambah(StudentService service, Scanner sc) {
        System.out.print("Berapa mahasiswa yang ingin ditambah? ");
        int jumlah = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= jumlah; i++) {
            System.out.println("\nData mahasiswa ke-" + i);

            System.out.print("Masukkan NPM: ");
            String npm = sc.nextLine();

            System.out.print("Masukkan Nama: ");
            String nama = sc.nextLine();

            System.out.print("Masukkan Umur: ");
            int umur = Integer.parseInt(sc.nextLine());

            service.add(new Student(npm, nama, umur));
            System.out.println("Mahasiswa ke-" + i + " berhasil ditambahkan.");
        }

        System.out.println("\nSemua mahasiswa berhasil ditambahkan.");
    }


    private static void lihatSemua(StudentService s) {
        List<Student> data = s.getAll();
        if (data.isEmpty()) System.out.println("Tidak ada data.");
        else data.forEach(System.out::println);
    }

    private static void cariNpm(StudentService s) {
        System.out.print("Masukkan NPM: ");
        String npm = sc.nextLine();
        Student st = s.findByNpm(npm);
        System.out.println(st != null ? st : "Data tidak ditemukan.");
    }

    private static void cariNama(StudentService s) {
        System.out.print("Masukkan nama: ");
        String nama = sc.nextLine();
        s.findByName(nama).forEach(System.out::println);
    }

    private static void update(StudentService s) {
        System.out.print("NPM yang ingin diupdate: ");
        String npm = sc.nextLine();

        System.out.print("Nama baru: ");
        String nama = sc.nextLine();
        System.out.print("Umur baru: ");
        int umur = Integer.parseInt(sc.nextLine());

        boolean ok = s.update(npm, new Student(npm, nama, umur));
        System.out.println(ok ? "Data berhasil diupdate." : "Data tidak ditemukan.");
    }

    private static void hapus(StudentService s) {
        System.out.print("Masukkan NPM untuk hapus: ");
        String npm = sc.nextLine();
        boolean ok = s.remove(npm);
        System.out.println(ok ? "Data terhapus." : "NPM tidak ditemukan.");
    }

    private static void pagination(StudentService s) {
        List<Student> data = s.getAll();
        if (data.isEmpty()) {
            System.out.println("Tidak ada data.");
            return;
        }

        int page = 0;
        int size = 3;

        while (true) {
            int start = page * size;
            int end = Math.min(start + size, data.size());

            System.out.println("\nPage " + (page + 1));
            for (int i = start; i < end; i++) System.out.println(data.get(i));

            System.out.println("[N]ext / [P]rev / [Q]uit");
            String cmd = sc.nextLine();

            if (cmd.equalsIgnoreCase("N") && end < data.size()) page++;
            else if (cmd.equalsIgnoreCase("P") && page > 0) page--;
            else if (cmd.equalsIgnoreCase("Q")) break;
        }
    }

}

