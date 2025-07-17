package main.app;


import main.model.Bookstore;
import main.model.BookGenre;
import main.model.Book;
import main.service.*;
import main.service.BookUI;
import main.service.BookFileHandler;
import main.service.UserFileHandler;
import main.service.EmployeeFileHandler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Bookstore bookstore = new Bookstore("Smart Bookstore");
        bookstore.getBooks().addAll(BookFileHandler.loadBooks());
        bookstore.getUsers().addAll(UserFileHandler.loadUsers());
        bookstore.getEmployees().addAll(EmployeeFileHandler.loadEmployees());


        BookService bookService = new BookService(bookstore);
        UserService userService = new UserService(bookstore);
        EmployeeService employeeService = new EmployeeService(bookstore);

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n=== Bookstore Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Remove Book");
            System.out.println("4. Add User");
            System.out.println("5. Display Users");
            System.out.println("6. Remove User");
            System.out.println("7. Add Employee");
            System.out.println("8. Display Employees");
            System.out.println("9. Remove Employee");
            System.out.println("10. Search books by author");
            System.out.println("11. Sort books");
            System.out.println("12. Purchase a book");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            option = scanner.nextInt();
            scanner.nextLine(); // flush newline

            switch (option) {
                case 1:
                    System.out.println("Enter title:");
                    String title = scanner.nextLine();

                    System.out.println("Enter author:");
                    String author = scanner.nextLine();

                    System.out.println("Enter year:");
                    int year = scanner.nextInt();

                    System.out.println("Enter price:");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // flush

                    System.out.println("Enter genre:");
                    String genre = scanner.nextLine();

                    System.out.println("Enter quantity:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // flush

                    try {
                        BookGenre genreEnum = BookGenre.valueOf(genre.toUpperCase());
                        Book book = new Book(title, author, year, price, genreEnum, quantity);
                        bookService.addBook(book);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid genre! Please use values like FICTION, NONFICTION, etc.");
                    }
                    break;

                case 2:
                    bookService.displayBooks();
                    break;

                case 3:
                    System.out.println("Enter title to remove:");
                    String removeTitle = scanner.nextLine();
                    bookService.removeBook(removeTitle);
                    break;

                case 4:
                    UserUI.addUserFromInput(bookstore);
                    break;

                case 5:
                    UserUI.printAllUsers(bookstore);
                    break;

                case 6:
                    System.out.println("Enter username to remove:");
                    String removeUsername = scanner.nextLine();
                    userService.removeUser(removeUsername);
                    break;

                case 7:
                    EmployeeUI.addEmployeeFromInput(bookstore);
                    break;

                case 8:
                    EmployeeUI.printAllEmployees(bookstore);
                    break;

                case 9:
                    System.out.println("Enter employee ID to remove:");
                    String removeId = scanner.nextLine();
                    employeeService.removeEmployee(removeId);
                    break;

                case 10:
                    BookUI.searchBooksByAuthor(bookstore);
                    break;

                case 11:
                    BookUI.sortBooks(bookstore);
                    break;

                case 12:
                    BookUI.purchaseBook(bookstore);
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }

        } while (option != 0);

        BookFileHandler.saveBooks(bookstore.getBooks());
        UserFileHandler.saveUsers(bookstore.getUsers());
        EmployeeFileHandler.saveEmployees(bookstore.getEmployees());

        scanner.close();
    }
}