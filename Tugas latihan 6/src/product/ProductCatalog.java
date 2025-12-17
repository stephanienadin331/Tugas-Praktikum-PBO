package product;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {

    private final Map<String, Product> catalog = new HashMap<>();

    public void add(Product p) {
        if (catalog.containsKey(p.getId())) {
            System.out.println("Product dengan ID ini sudah ada!");
            return;
        }
        catalog.put(p.getId(), p);
        System.out.println(" Produk berhasil ditambahkan!");
    }

    public void update(String id, String newName, double newPrice) {
        Product p = catalog.get(id);
        if (p == null) {
            System.out.println(" Produk tidak ditemukan!");
            return;
        }
        p.setName(newName);
        p.setPrice(newPrice);
        System.out.println(" Produk berhasil diupdate!");
    }

    public void remove(String id) {
        if (catalog.remove(id) != null)
            System.out.println(" Produk berhasil dihapus!");
        else
            System.out.println(" Produk tidak ditemukan!");
    }

    public Product get(String id) {
        return catalog.get(id);
    }

    public void showAll() {
        if (catalog.isEmpty()) {
            System.out.println("Tidak ada produk.");
            return;
        }
        System.out.println("\n===== DAFTAR PRODUK =====");
        for (Product p : catalog.values()) {
            System.out.println(p);
        }
    }
}

