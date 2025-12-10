import java.util.*;

// ==========================================================
// ABSTRACT CLASS : DOCUMENT
// ==========================================================
abstract class Document {
    protected String title;
    protected int pages;

    public Document(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    public abstract void print();
}

// ==========================================================
// PDF DOCUMENT
// ==========================================================
class PdfDocument extends Document {

    public PdfDocument(String title, int pages) {
        super(title, pages);
    }

    @Override
    public void print() {
        System.out.println("[PDF] Mencetak Dokumen");
        System.out.println("Judul           : " + title);
        System.out.println("Jumlah Halaman  : " + pages);
        System.out.println("Output          : Rendering PDF...");
        System.out.println("--------------------------------------------------");
    }
}

// ==========================================================
// WORD DOCUMENT
// ==========================================================
class WordDocument extends Document {

    public WordDocument(String title, int pages) {
        super(title, pages);
    }

    @Override
    public void print() {
        System.out.println("[WORD] Mencetak Dokumen");
        System.out.println("Judul           : " + title);
        System.out.println("Jumlah Halaman  : " + pages);
        System.out.println("Output          : Rendering Word...");
        System.out.println("--------------------------------------------------");
    }
}

// ==========================================================
// PRINTER
// ==========================================================
class Printer {

    public void printAll(List<Document> docs) {
        System.out.println("\n==================================================");
        System.out.println("                 MENCETAK SEMUA DOKUMEN           ");
        System.out.println("==================================================");

        for (Document d : docs) {
            d.print();
        }
    }
}

// ==========================================================
// STRATEGY PATTERN : TAX CALCULATOR
// ==========================================================
interface TaxCalculator {
    double calculate(double salary);
}

class FlatTax implements TaxCalculator {

    private double rate;

    public FlatTax(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculate(double salary) {
        return salary * rate;
    }
}

class ProgressiveTax implements TaxCalculator {

    @Override
    public double calculate(double salary) {
        if (salary <= 3000) return salary * 0.05;
        else if (salary <= 7000) return salary * 0.10;
        else return salary * 0.15;
    }
}

// ==========================================================
// EMPLOYEE
// ==========================================================
class Employee {

    private String name;
    private double salary;
    private TaxCalculator taxStrategy;

    public Employee(String name, double salary, TaxCalculator strategy) {
        this.name = name;
        this.salary = salary;
        this.taxStrategy = strategy;
    }

    public void setStrategy(TaxCalculator strategy) {
        this.taxStrategy = strategy;
    }

    public void printTax() {
        System.out.println("\n==================================================");
        System.out.println("              PERHITUNGAN PAJAK KARYAWAN         ");
        System.out.println("==================================================");

        System.out.println("Nama Karyawan : " + name);
        System.out.println("Gaji          : " + salary);
        System.out.println("Pajak Dihitung: " + taxStrategy.calculate(salary));
        System.out.println("--------------------------------------------------");
    }
}

// ==========================================================
// MAIN PROGRAM
// ==========================================================
public class Main{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Document> documents = new ArrayList<>();

        System.out.println("==================================================");
        System.out.println("           SISTEM DOKUMEN & PRINTER              ");
        System.out.println("==================================================");

        System.out.print("Berapa dokumen yang ingin dibuat? : ");
        int jumlah = sc.nextInt();
        sc.nextLine();   // clear buffer

        // ========== INPUT DOKUMEN ==========
        for (int i = 1; i <= jumlah; i++) {

            System.out.println("\n--------------------------------------------------");
            System.out.println("                DOKUMEN KE-" + i);
            System.out.println("--------------------------------------------------");

            System.out.print("Judul Dokumen     : ");
            String title = sc.nextLine();

            System.out.print("Jumlah Halaman    : ");
            int pages = sc.nextInt();

            System.out.println("\nPilih Jenis Dokumen:");
            System.out.println("1. PDF Document");
            System.out.println("2. Word Document");
            System.out.print("Pilihan           : ");
            int jenis = sc.nextInt();
            sc.nextLine(); // bersihkan buffer

            if (jenis == 1) {
                documents.add(new PdfDocument(title, pages));
            } else {
                documents.add(new WordDocument(title, pages));
            }
        }

        // ========== CETAK SEMUA DOKUMEN ==========
        Printer printer = new Printer();
        printer.printAll(documents);

        // ========== BAGIAN PAJAK ==========
        System.out.println("\n==================================================");
        System.out.println("            SISTEM PERHITUNGAN PAJAK             ");
        System.out.println("==================================================");

        System.out.print("Nama Karyawan : ");
        String name = sc.nextLine();

        System.out.print("Gaji          : ");
        double salary = sc.nextDouble();

        System.out.println("\nPilih Strategi Pajak:");
        System.out.println("1. Flat Tax (10%)");
        System.out.println("2. Progressive Tax");
        System.out.print("Pilihan        : ");
        int opt = sc.nextInt();

        TaxCalculator strategy =
                (opt == 1) ? new FlatTax(0.10) : new ProgressiveTax();

        Employee emp = new Employee(name, salary, strategy);
        emp.printTax();

        // ========== GANTI STRATEGI PAJAK ==========
        System.out.println("\nApakah ingin mengganti strategi pajak?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        System.out.print("Pilihan        : ");
        int ubah = sc.nextInt();

        if (ubah == 1) {
            System.out.println("\nPilih Strategi Baru:");
            System.out.println("1. Flat Tax (10%)");
            System.out.println("2. Progressive Tax");
            System.out.print("Pilihan Baru   : ");
            int newOpt = sc.nextInt();

            if (newOpt == 1) emp.setStrategy(new FlatTax(0.10));
            else emp.setStrategy(new ProgressiveTax());

            emp.printTax();
        }

        System.out.println("\n==================================================");
        System.out.println("                  PROGRAM SELESAI                 ");
        System.out.println("==================================================");
    }
}

