package main.app;


import main.model.Bookstore;
import main.model.BookGenre;
import main.model.Book;
import main.model.User;
import main.service.*;
import main.service.BookUI;
import main.service.BookFileHandler;
import main.service.UserFileHandler;
import main.service.EmployeeFileHandler;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static User activeUser = null;

    public static void main(String[] args) {

        Bookstore bookstore = new Bookstore("Smart Bookstore");
        User activeUser = null;

        bookstore.getBooks().addAll(BookFileHandler.loadBooks());
        bookstore.getUsers().addAll(UserFileHandler.loadUsers());
        bookstore.getEmployees().addAll(EmployeeFileHandler.loadEmployees());



        BookService bookService = new BookService(bookstore);
        UserService userService = new UserService(bookstore);
        EmployeeService employeeService = new EmployeeService(bookstore);

        Scanner scanner = new Scanner(System.in);
        int option = -1;

        do {
            System.out.println("\n=== Bookstore Menu ===");
            System.out.println("L. Login as existing user");
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
            System.out.println("13. View purchase history");
            System.out.println("14. View top-selling books");
            System.out.println("15. View all purchases (admin)");
            System.out.println("16. View users who purchased a specific book");
            System.out.println("17. Purchase multiple books (cart)");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            System.out.print("Choose option: ");
            String rawInput = scanner.nextLine(); // čita kao string

            if (rawInput.equalsIgnoreCase("L")) {
                System.out.print("Enter username: ");
                String inputUsername = scanner.nextLine();

                Optional<User> user = bookstore.getUsers().stream()
                        .filter(u -> u.getUsername().equalsIgnoreCase(inputUsername))
                        .findFirst();

                if (user.isPresent()) {
                    activeUser = user.get();
                    System.out.println("Logged in as: " + activeUser.getUsername());
                } else {
                    System.out.println("User not found.");
                }

                continue;
            }

            try {
                option = Integer.parseInt(rawInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

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

                case 13:
                    BookUI.showUserPurchaseHistory();
                    break;

                case 14:
                    BookUI.showTopSellingBooks();
                    break;

                case 15:
                    BookUI.showAllPurchases();
                    break;

                case 16:
                    BookUI.showUsersWhoPurchasedBook();
                    break;

                case 17:
                    BookUI.purchaseMultipleBooks(bookstore);
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