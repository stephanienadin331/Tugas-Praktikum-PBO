import java.util.*;

public class BankApp {

    // ============================
    //   CLASS : BankAccount
    // ============================
    static class BankAccount {
        private String accountNumber;
        private String owner;
        private double balance;

        private List<String> transactions = new ArrayList<>();

        public BankAccount(String accountNumber, String owner, double initialBalance) {
            this.accountNumber = accountNumber;
            this.owner = owner;
            this.balance = initialBalance;
            transactions.add("[CREATED] Saldo awal: " + initialBalance);
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getOwner() {
            return owner;
        }

        public double getBalance() {
            return balance;
        }

        public List<String> getTransactions() {
            return Collections.unmodifiableList(new ArrayList<>(transactions));
        }

        public void addTransaction(String note) {
            transactions.add(note);
        }

        public void deposit(double amount) {
            balance += amount;
            transactions.add("[DEPOSIT] +" + amount + " | Saldo: " + balance);
        }

        public boolean withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
                transactions.add("[WITHDRAW] -" + amount + " | Saldo: " + balance);
                return true;
            }
            transactions.add("[FAILED] Withdraw " + amount + " (Saldo tidak cukup)");
            return false;
        }

        @Override
        public String toString() {
            return String.format("%-10s | %-10s | Rp %.2f", accountNumber, owner, balance);
        }
    }

    // ============================
    //         CLASS : Bank
    // ============================
    static class Bank {
        private Map<String, BankAccount> accounts = new HashMap<>();

        public void addAccount(BankAccount acc) {
            accounts.put(acc.getAccountNumber(), acc);
        }

        public BankAccount getAccount(String accNum) {
            return accounts.get(accNum);
        }

        public boolean transfer(String fromAcc, String toAcc, double amount) {
            BankAccount from = accounts.get(fromAcc);
            BankAccount to = accounts.get(toAcc);

            if (from == null || to == null) return false;

            if (from.withdraw(amount)) {
                to.deposit(amount);
                from.addTransaction("[TRANSFER OUT] ke " + toAcc + " -" + amount);
                to.addTransaction("[TRANSFER IN] dari " + fromAcc + " +" + amount);
                return true;
            }
            return false;
        }

        public void printAllBalances() {
            System.out.println("\n+============================================+");
            System.out.println("|             DAFTAR SALDO AKUN             |");
            System.out.println("+============================================+");
            System.out.printf("%-10s | %-10s | %-10s\n", "Akun", "Pemilik", "Saldo");
            System.out.println("----------------------------------------------");

            for (BankAccount acc : accounts.values()) {
                System.out.printf("%-10s | %-10s | Rp %.2f\n",
                        acc.getAccountNumber(), acc.getOwner(), acc.getBalance());
            }
            System.out.println("----------------------------------------------");
        }
    }

    // ============================
    //           MAIN APP
    // ============================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.println("======================================");
        System.out.println("         INPUT PEMBUATAN AKUN         ");
        System.out.println("======================================");

        System.out.print("Berapa akun yang ingin dibuat? ");
        int jumlah = sc.nextInt();

        for (int i = 1; i <= jumlah; i++) {
            System.out.println("\n=== Buat Akun ke-" + i + " ===");

            System.out.print("Nomor Akun     : ");
            String accNum = sc.next();

            System.out.print("Nama Pemilik   : ");
            String owner = sc.next();

            System.out.print("Saldo Awal     : ");
            double saldo = sc.nextDouble();

            bank.addAccount(new BankAccount(accNum, owner, saldo));
            System.out.println(">> Akun berhasil dibuat!");
        }

        int pilih;

        do {
            System.out.println("\n====================================");
            System.out.println("              MENU BANK             ");
            System.out.println("====================================");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Lihat Semua Saldo");
            System.out.println("5. Lihat Riwayat Transaksi");
            System.out.println("0. Keluar");
            System.out.println("====================================");
            System.out.print("Pilih menu: ");
            pilih = sc.nextInt();

            switch (pilih) {

                case 1:
                    System.out.println("\n--- Deposit ---");
                    System.out.print("Nomor Akun: ");
                    String accD = sc.next();
                    System.out.print("Jumlah Deposit: ");
                    double dep = sc.nextDouble();

                    BankAccount d = bank.getAccount(accD);
                    if (d != null) {
                        d.deposit(dep);
                        System.out.println(">> Deposit sukses!");
                    } else {
                        System.out.println(">> Akun tidak ditemukan!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Withdraw ---");
                    System.out.print("Nomor Akun: ");
                    String accW = sc.next();
                    System.out.print("Jumlah Withdraw: ");
                    double wd = sc.nextDouble();

                    BankAccount w = bank.getAccount(accW);
                    if (w != null) {
                        if (w.withdraw(wd))
                            System.out.println(">> Withdraw sukses!");
                        else
                            System.out.println(">> Saldo tidak cukup!");
                    } else {
                        System.out.println(">> Akun tidak ditemukan!");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Transfer ---");
                    System.out.print("Dari Akun: ");
                    String from = sc.next();
                    System.out.print("Ke Akun: ");
                    String to = sc.next();
                    System.out.print("Jumlah Transfer: ");
                    double tf = sc.nextDouble();

                    if (bank.transfer(from, to, tf))
                        System.out.println(">> Transfer berhasil!");
                    else
                        System.out.println(">> Transfer gagal!");
                    break;

                case 4:
                    bank.printAllBalances();
                    break;

                case 5:
                    System.out.println("\n--- Riwayat Transaksi ---");
                    System.out.print("Nomor Akun: ");
                    String accT = sc.next();
                    BankAccount acc = bank.getAccount(accT);

                    if (acc != null) {
                        System.out.println("\nRiwayat Transaksi - " + accT);
                        System.out.println("----------------------------------");
                        for (String t : acc.getTransactions()) {
                            System.out.println(t);
                        }
                        System.out.println("----------------------------------");
                    } else {
                        System.out.println(">> Akun tidak ditemukan!");
                    }
                    break;
            }

        } while (pilih != 0);

        System.out.println("\nTerima kasih telah menggunakan bank kami!");
        System.out.println("Program selesai.");
    }
}

