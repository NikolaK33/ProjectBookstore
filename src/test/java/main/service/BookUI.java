package main.service;

import main.model.Book;
import main.model.BookGenre;
import main.model.Bookstore;

import java.util.Scanner;

public class BookUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void addBookFromInput(Bookstore bookstore) {
        System.out.println("Enter title:");
        String title = scanner.nextLine();

        System.out.println("Enter author:");
        String author = scanner.nextLine();

        System.out.println("Enter year:");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter price:");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter genre (e.g., FICTION, NONFICTION, SCIFI, BIOGRAPHY):");
        String genreInput = scanner.nextLine();

        System.out.println("Enter quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        try {
            BookGenre genre = BookGenre.valueOf(genreInput.toUpperCase());
            Book book = new Book(title, author, year, price, genre, quantity);
            bookstore.addBook(book);
            LoggerService.log("Book added: " + book.getTitle() + " by " + book.getAuthor());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid genre! Please try again.");
        }
    }

    public static void printAllBooks(Bookstore bookstore) {
        System.out.println("\n=== Book List ===");
        if (bookstore.getBooks().isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : bookstore.getBooks()) {
                System.out.println(book);
            }
        }
    }

    public static void removeBookByTitle(Bookstore bookstore) {
        System.out.println("Enter title:");
        String title = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();

        bookstore.removeBook(title, author);
        LoggerService.log("Attempted to remove book: " + title);
    }
}