package main.service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PurchaseLogger {

    private static final String FILE_PATH = "purchases.txt";

    public static void logPurchase(String username, String bookTitle) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(username + ";" + bookTitle + ";" + LocalDateTime.now());
        } catch (IOException e) {
            System.out.println("Failed to log purchase: " + e.getMessage());
        }
    }

    public static void showPurchaseHistory(String username) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No purchase history found.");
            return;
        }

        System.out.println("\n=== Purchase History for " + username + " ===");

        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && parts[0].equalsIgnoreCase(username)) {
                    System.out.println("• " + parts[1] + " (on " + parts[2] + ")");
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading purchase history: " + e.getMessage());
        }

        if (!found) {
            System.out.println("No purchases found for this user.");
        }
    }

    public static void showTopSellingBooks() {
        Map<String, Integer> bookSales = new HashMap<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No purchase data found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String bookTitle = parts[1];
                    bookSales.put(bookTitle, bookSales.getOrDefault(bookTitle, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading purchase data: " + e.getMessage());
            return;
        }

        if (bookSales.isEmpty()) {
            System.out.println("No books sold yet.");
            return;
        }

        System.out.println("\n=== Top Selling Books ===");
        bookSales.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry ->
                        System.out.println(entry.getKey() + " – sold: " + entry.getValue() + " time(s)")
                );
    }

    public static void showAllPurchases() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No purchases recorded.");
            return;
        }

        System.out.println("\n=== All Purchases ===");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    System.out.println("User: " + parts[0] + " | Book: " + parts[1] + " | Date: " + parts[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading purchases: " + e.getMessage());
        }
    }
}