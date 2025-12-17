package product;

public class TestProduct {
    public static void main(String[] args) {

        ProductCatalog catalog = new ProductCatalog();

        // Test add
        catalog.add(new Product("P01", "Kopi", 15000));
        catalog.add(new Product("P02", "Teh", 12000));

        // Test duplicate add
        catalog.add(new Product("P01", "Air", 5000));

        // Test update
        catalog.update("P02", "Teh Botol", 14000);

        // Test remove
        catalog.remove("P01");

        // Test get
        Product p = catalog.get("P02");
        System.out.println("Hasil GET: " + p);

        // Show all
        catalog.showAll();
    }
}

