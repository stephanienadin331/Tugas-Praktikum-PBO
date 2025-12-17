package Email;

import java.io.*;
import java.util.*;

public class EmailDeduplicator {

    public static void processFile(String inputFile, String outputFile) {
        TreeSet<String> uniqueEmails = new TreeSet<>(new EmailComparator());

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {

                String email = line.trim();
                if (email.isEmpty()) continue;

                // NORMALISASI Email
                String normalized = normalize(email);

                uniqueEmails.add(normalized);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String email : uniqueEmails) {
                bw.write(email);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menulis file: " + e.getMessage());
        }
    }

    // ============================
    // NORMALISASI EMAIL
    // ============================
    private static String normalize(String email) {
        String[] parts = email.split("@");
        String local = parts[0].toLowerCase();
        String domain = parts[1].toLowerCase();
        return local + "@" + domain;
    }
}
