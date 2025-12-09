import java.util.*;

// ================================ PRODUCT ================================
class Product {
    private final String id;    // immutable
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', price=%.2f}", id, name, price);
    }
}

// ============================ PRODUCT CATALOG ============================
class ProductCatalog {
    private Map<String, Product> catalog = new HashMap<>();

    public void add(Product p) {
        catalog.put(p.getId(), p);
    }

    public void update(String id, String newName, double newPrice) {
        Product p = catalog.get(id);
        if (p != null) {
            p.setName(newName);
            p.setPrice(newPrice);
        } else {
            System.out.println("Produk dengan ID tersebut tidak ditemukan!");
        }
    }

    public void remove(String id) {
        if (catalog.remove(id) == null) {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    public Product get(String id) {
        return catalog.get(id);
    }

    public void printAll() {
        System.out.println("\n===================== DAFTAR PRODUK =====================");

        if (catalog.isEmpty()) {
            System.out.println("Belum ada produk.");
            return;
        }

        System.out.printf("%-15s| %-25s| %-15s|\n", "ID PRODUK" , "NAMA PRODUK" , "HARGA"      );
        System.out.println("--------------------------------------------------------------");

        for (Product p : catalog.values()) {
            System.out.printf("%-15s| %-25s| Rp %-12.2f|\n",
                    p.getId(),
                    p.getName(),
                    p.getPrice());
        }

        System.out.println("--------------------------------------------------------------");
    }

}

// ================================ EMAIL DEDUPLICATOR ================================
class EmailDeduplicator {
    private static final Comparator<String> EMAIL_COMPARATOR = (a, b) -> {
        String localA = a.split("@")[0].toLowerCase();
        String localB = b.split("@")[0].toLowerCase();
        int cmp = localA.compareTo(localB);
        if (cmp == 0) return a.compareToIgnoreCase(b);
        return cmp;
    };

    public static TreeSet<String> processEmails(List<String> emails) {
        return new TreeSet<>(EMAIL_COMPARATOR) {{
            addAll(emails);
        }};
    }
}

// ================================ MAIN PROGRAM ================================
public class SetMap {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ProductCatalog catalog = new ProductCatalog();
        int menu;

        do {
            System.out.println("\n========== MENU UTAMA ==========");
            System.out.println("1. Email Deduplicator");
            System.out.println("2. Manajemen Product Catalog");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {

                // ================= EMAIL DEDUPLICATOR ====================
                case 1:
                    System.out.print("Berapa banyak email yang ingin diinput? ");
                    int n = sc.nextInt();
                    sc.nextLine();

                    List<String> emails = new ArrayList<>();

                    for (int i = 0; i < n; i++) {
                        System.out.print("Email ke-" + (i + 1) + ": ");
                        emails.add(sc.nextLine());
                    }

                    TreeSet<String> unique = EmailDeduplicator.processEmails(emails);

                    System.out.println("\n=== EMAIL UNIK (DISORTIR) ===");
                    unique.forEach(System.out::println);
                    break;

                // ================= PRODUCT CATALOG =======================
                case 2:
                    int sub;
                    do {
                        System.out.println("\n------ MENU PRODUCT ------");
                        System.out.println("1. Tambah Product");
                        System.out.println("2. Update Product");
                        System.out.println("3. Hapus Product");
                        System.out.println("4. Cari Product (Get)");
                        System.out.println("5. Lihat Semua Produk");
                        System.out.println("0. Kembali");
                        System.out.print("Pilih: ");
                        sub = sc.nextInt();
                        sc.nextLine();

                        switch (sub) {
                            case 1:
                                System.out.print("Masukkan ID Produk: ");
                                String id = sc.nextLine();

                                System.out.print("Masukkan Nama Produk: ");
                                String name = sc.nextLine();

                                System.out.print("Masukkan Harga Produk: ");
                                double price = sc.nextDouble();
                                sc.nextLine();

                                catalog.add(new Product(id, name, price));
                                System.out.println("Produk berhasil ditambahkan!");
                                break;

                            case 2:
                                System.out.print("Masukkan ID Produk yang ingin diupdate: ");
                                String uid = sc.nextLine();

                                System.out.print("Nama Baru: ");
                                String newName = sc.nextLine();

                                System.out.print("Harga Baru: ");
                                double newPrice = sc.nextDouble();
                                sc.nextLine();

                                catalog.update(uid, newName, newPrice);
                                break;

                            case 3:
                                System.out.print("Masukkan ID Produk yang ingin dihapus: ");
                                String rid = sc.nextLine();
                                catalog.remove(rid);
                                break;

                            case 4:
                                System.out.println("\n=== DAFTAR PRODUK SAAT INI ===");
                                catalog.printAll();

                                System.out.print("\nMasukkan ID Produk yang dicari: ");
                                String gid = sc.nextLine();
                                Product p = catalog.get(gid);

                                System.out.println("\n=== HASIL PENCARIAN ===");
                                if (p == null) {
                                    System.out.println("Produk dengan ID '" + gid + "' tidak ditemukan.");
                                } else {
                                    System.out.printf("%-15s %-25s Rp %-12.2f\n",
                                            p.getId(),
                                            p.getName(),
                                            p.getPrice());
                                }
                                break;

                            case 5:
                                catalog.printAll();
                                break;
                        }
                    } while (sub != 0);
                    break;

            }

        } while (menu != 0);

        System.out.println("\nProgram selesai.");
        sc.close();
    }
}
