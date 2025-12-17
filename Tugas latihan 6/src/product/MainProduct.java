package product;

import java.util.Scanner;

class Color {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
}

public class MainProduct {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductCatalog catalog = new ProductCatalog();

        while (true) {
            System.out.println(Color.BLUE +
                    "\n╔═════════════════════════════╗\n" +
                    "║       MENU PRODUK           ║\n" +
                    "╠═════════════════════════════╣\n" +
                    "║ 1. Tambah Produk            ║\n" +
                    "║ 2. Update Produk            ║\n" +
                    "║ 3. Hapus Produk             ║\n" +
                    "║ 4. Cari Produk (by ID)      ║\n" +
                    "║ 5. Tampilkan Semua Produk   ║\n" +
                    "║ 0. Keluar                   ║\n" +
                    "╚═════════════════════════════╝" +
                    Color.RESET);

            System.out.print(Color.YELLOW + "Pilih menu: " + Color.RESET);
            int pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1:
                    System.out.print("Masukkan ID produk: ");
                    String addId = sc.nextLine();
                    System.out.print("Masukkan Nama produk: ");
                    String addName = sc.nextLine();
                    System.out.print("Masukkan Harga produk: ");
                    double addPrice = sc.nextDouble();
                    sc.nextLine();

                    catalog.add(new Product(addId, addName, addPrice));
                    break;

                case 2:
                    System.out.print("Masukkan ID produk yang diupdate: ");
                    String updId = sc.nextLine();
                    System.out.print("Nama baru produk: ");
                    String newName = sc.nextLine();
                    System.out.print("Harga baru produk: ");
                    double newPrice = sc.nextDouble();
                    sc.nextLine();

                    catalog.update(updId, newName, newPrice);
                    break;

                case 3:
                    System.out.print("Masukkan ID produk yang dihapus: ");
                    String delId = sc.nextLine();
                    catalog.remove(delId);
                    break;

                case 4:
                    System.out.print("Masukkan ID produk: ");
                    String getId = sc.nextLine();
                    Product p = catalog.get(getId);

                    if (p != null)
                        System.out.println(Color.GREEN + p + Color.RESET);
                    else
                        System.out.println(Color.RED + "Produk tidak ditemukan!" + Color.RESET);

                    break;

                case 5:
                    catalog.showAll();
                    break;

                case 0:
                    System.out.println(Color.GREEN + "Keluar dari program..." + Color.RESET);
                    return;

                default:
                    System.out.println(Color.RED + "Menu tidak valid!" + Color.RESET);
            }
        }
    }
}

