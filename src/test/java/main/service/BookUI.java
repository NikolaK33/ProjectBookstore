package main.service;

import lombok.extern.java.Log;
import main.model.Book;
import main.model.BookGenre;
import main.model.Bookstore;

import java.util.Scanner;

public class BookUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void addBookFromInput(Bookstore bookstore) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        System.out.print("Enter book price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Available genres: ");
        for (BookGenre genre : BookGenre.values()) {
            System.out.println("- " + genre);
        }

        System.out.print("Enter book genre (uppercase): ");
        BookGenre genre = BookGenre.valueOf(scanner.nextLine().toUpperCase());

        Book book = new Book(title, author, price, genre);
        bookstore.addBook(book);
        main.service.LoggerService.log("Book added: " + book.getTitle());
    }

    public static void printAllBooks(Bookstore bookstore) {
        System.out.println("Books in bookstore:");
        for (Book book : bookstore.getBooks()) {
            System.out.println(book);
        }
    }

}