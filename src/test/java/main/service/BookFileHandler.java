package main.service;

import main.model.Book;
import main.model.BookGenre;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookFileHandler {

    private static final String FILE_PATH = "books.txt";

    public static void saveBooks(List<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Book book : books) {
                writer.println(book.getTitle() + ";" +
                        book.getAuthor() + ";" +
                        book.getYear() + ";" +
                        book.getPrice() + ";" +
                        book.getGenre() + ";" +
                        book.getQuantity());
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) return books;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    String title = parts[0];
                    String author = parts[1];
                    int year = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    BookGenre genre = BookGenre.valueOf(parts[4]);
                    int quantity = Integer.parseInt(parts[5]);

                    books.add(new Book(title, author, year, price, genre, quantity));
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }

        return books;
    }
}