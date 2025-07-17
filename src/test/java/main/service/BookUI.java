package main.service;

import main.model.Book;
import main.model.BookGenre;
import main.model.Bookstore;
import main.model.User;
import main.app.Main;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    public static void searchBooksByAuthor(Bookstore bookstore) {
        System.out.print("Enter author name to search: ");
        String author = scanner.nextLine();

        boolean found = false;
        for (Book book : bookstore.getBooks()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found by that author.");
        }
    }

    public static void sortBooks(Bookstore bookstore) {
        System.out.println("Sort books by:");
        System.out.println("1. Title (A-Z)");
        System.out.println("2. Price (Low to High)");
        System.out.println("3. Year (Newest First)");
        System.out.print("Choose option: ");

        int choice = Integer.parseInt(scanner.nextLine());

        List<Book> books = new ArrayList<>(bookstore.getBooks());

        switch (choice) {
            case 1:
                books.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
                break;
            case 2:
                books.sort(Comparator.comparingDouble(Book::getPrice));
                break;
            case 3:
                books.sort(Comparator.comparingInt(Book::getYear).reversed());
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        System.out.println("\n=== Sorted Books ===");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void purchaseBook(Bookstore bookstore) {
        String username = Main.activeUser != null ? Main.activeUser.getUsername() : null;


        if (username == null) {
            System.out.println("You must log in first.");
            return;
        }

        boolean userExists = bookstore.getUsers().stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));

        if (!userExists) {
            System.out.println("User not found. Please register first.");
            return;
        }

        System.out.print("Enter title of the book to purchase: ");
        String title = scanner.nextLine();

        for (Book book : bookstore.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.getQuantity() > 0) {
                    book.setQuantity(book.getQuantity() - 1);
                    System.out.println("Book purchased: " + book.getTitle());

                    LoggerService.log("Book purchased: " + book.getTitle() + " by " + username);
                    PurchaseLogger.logPurchase(username, book.getTitle());
                } else {
                    System.out.println("Sorry, the book is out of stock.");
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }

    public static void showUserPurchaseHistory() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        PurchaseLogger.showPurchaseHistory(username);
    }

    public static void showTopSellingBooks() {
        PurchaseLogger.showTopSellingBooks();
    }

    public static void showAllPurchases() {
        PurchaseLogger.showAllPurchases();
    }

    public static void showUsersWhoPurchasedBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        PurchaseLogger.showUsersWhoPurchasedBook(title);
    }

    public static void purchaseMultipleBooks(Bookstore bookstore) {
        String username = Main.activeUser != null ? Main.activeUser.getUsername() : null;

        if (username == null) {
            System.out.println("You must log in first.");
            return;
        }

        List<String> requestedTitles = new ArrayList<>();

        System.out.println("Enter book titles to purchase (one per line). Type 'done' to finish:");

        while (true) {
            String title = scanner.nextLine();
            if (title.equalsIgnoreCase("done")) break;
            requestedTitles.add(title);
        }

        List<String> successful = new ArrayList<>();
        List<String> failed = new ArrayList<>();

        for (String title : requestedTitles) {
            boolean found = false;

            for (Book book : bookstore.getBooks()) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    found = true;
                    if (book.getQuantity() > 0) {
                        book.setQuantity(book.getQuantity() - 1);
                        successful.add(book.getTitle());
                        LoggerService.log("Book purchased (cart): " + book.getTitle() + " by " + username);
                        PurchaseLogger.logPurchase(username, book.getTitle());
                    } else {
                        failed.add(book.getTitle() + " (out of stock)");
                    }
                    break;
                }
            }

            if (!found) {
                failed.add(title + " (not found)");
            }
        }

        System.out.println("\n=== Purchase Summary ===");
        if (!successful.isEmpty()) {
            System.out.println("Purchased:");
            successful.forEach(s -> System.out.println("• " + s));
        } else {
            System.out.println("No successful purchases.");
        }

        if (!failed.isEmpty()) {
            System.out.println("\nNot purchased:");
            failed.forEach(f -> System.out.println("• " + f));
        }
    }
}