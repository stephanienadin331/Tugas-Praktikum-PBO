import java.util.Scanner;

public class Main {

    private static void printHeader() {
        System.out.println(Color.GREEN +
                "╔════════════════════════════════════════════════╗\n" +
                "║           STUDENT MANAGEMENT SYSTEM            ║\n" +
                "╚════════════════════════════════════════════════╝" +
                Color.RESET);
    }

    private static void printMenu() {
        System.out.println(Color.BLUE +
                "\n╔════════════ MENU UTAMA ════════════╗\n" +
                "║ 1. Tambah Student                  ║\n" +
                "║ 2. Update Student                  ║\n" +
                "║ 3. Hapus Student                   ║\n" +
                "║ 4. Tampilkan Semua Student         ║\n" +
                "║ 5. Tampilkan Log Perubahan         ║\n" +
                "║ 6. Jalankan Benchmark              ║\n" +
                "║ 0. Keluar                          ║\n" +
                "╚════════════════════════════════════╝" +
                Color.RESET);

        System.out.print(Color.YELLOW + "Pilih menu: " + Color.RESET);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentRepository repo = new StudentRepository();

        while (true) {
            printHeader();
            printMenu();

            int pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {

                case 1:
                    System.out.println("\n┌──────────────────────────────────────────┐");
                    System.out.println("│         TAMBAH STUDENT (BANYAK)          │");
                    System.out.println("├──────────────────────────────────────────┤");
                    System.out.print("│ Mau menambahkan berapa student? : ");
                    int jumlahTambah = sc.nextInt();
                    sc.nextLine();
                    System.out.println("└──────────────────────────────────────────┘");

                    for (int i = 1; i <= jumlahTambah; i++) {

                        System.out.println("\n● Input Student ke-" + i);
                        System.out.println("--------------------------------------");

                        System.out.print("Masukkan ID    : ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Masukkan Nama  : ");
                        String name = sc.nextLine();

                        System.out.print("Masukkan Umur  : ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        repo.add(new Student(id, name, age));

                        System.out.println(Color.GREEN + "✔ Student ke-" + i + " berhasil ditambahkan!" + Color.RESET);
                    }

                    System.out.println(Color.CYAN + "\nSelesai menambahkan " + jumlahTambah + " student!" + Color.RESET);
                    break;

                case 2:
                    System.out.println("\n┌──────────────────────────────────────────┐");
                    System.out.println("│          UPDATE STUDENT (BANYAK)         │");
                    System.out.println("├──────────────────────────────────────────┤");
                    System.out.print("│ Mau update berapa student? : ");
                    int jumlahUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.println("└──────────────────────────────────────────┘");

                    for (int i = 1; i <= jumlahUpdate; i++) {

                        System.out.println("\n● Update Student ke-" + i);
                        System.out.println("--------------------------------------");

                        System.out.print("Masukkan ID student yang diupdate : ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nama baru : ");
                        String newName = sc.nextLine();

                        System.out.print("Umur baru : ");
                        int newAge = sc.nextInt();
                        sc.nextLine();

                        repo.update(id, newName, newAge);

                        System.out.println(Color.GREEN + "✔ Student ke-" + i + " berhasil diupdate!" + Color.RESET);
                    }

                    System.out.println(Color.CYAN + "\nSelesai mengupdate " + jumlahUpdate + " student!" + Color.RESET);
                    break;

                case 3:
                    System.out.println("\n┌──────────────────────────────────────────┐");
                    System.out.println("│              HAPUS STUDENT               │");
                    System.out.println("├──────────────────────────────────────────┤");
                    System.out.print("│ Masukkan ID student yang akan dihapus : ");
                    int idHapus = sc.nextInt();
                    System.out.println("└──────────────────────────────────────────┘");

                    repo.remove(idHapus);

                    System.out.println(Color.GREEN + "✔ Student dengan ID " + idHapus + " berhasil dihapus!" + Color.RESET);
                    break;

                case 4:
                    repo.listStudents();
                    break;

                case 5:
                    repo.showLogs();
                    break;

                case 6:
                    Benchmark.run();
                    break;

                case 0:
                    System.out.println("\n┌──────────────────────────────────────────┐");
                    System.out.println("│       TERIMA KASIH TELAH MENGGUNAKAN     │");
                    System.out.println("│            PROGRAM STUDENT APP            │");
                    System.out.println("└──────────────────────────────────────────┘");
                    return;

                default:
                    System.out.println(Color.RED + "Menu tidak valid!" + Color.RESET);
                    break;
            }

        }
    }
}

