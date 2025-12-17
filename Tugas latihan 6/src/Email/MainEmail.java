package Email;

import java.util.Scanner;

public class MainEmail {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nama file input: ");
        String input = sc.nextLine();

        System.out.print("Nama file output: ");
        String output = sc.nextLine();

        EmailDeduplicator.processFile(input, output);

        System.out.println("Proses selesai. File unik tersimpan di: " + output);
    }
}

