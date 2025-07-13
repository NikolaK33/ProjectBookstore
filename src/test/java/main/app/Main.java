package main.app;

import main.model.Bookstore;
import main.model.Employee;
import main.model.User;
import main.service.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Bookstore bookstore = new Bookstore("Pametna Knjizara");

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

                    bookService.addBook(new main.model.Book(title, author, year, price,
                            main.model.BookGenre.valueOf(genre.toUpperCase()), quantity));
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
                    System.out.println("Enter user name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter username:");
                    String username = scanner.nextLine();
                    System.out.println("Enter password:");
                    String password = scanner.nextLine();
                    System.out.println("Enter email:");
                    String email = scanner.nextLine();
                    userService.addUser(new User(name, username, password, email));
                    break;

                case 5:
                    userService.displayUsers();
                    break;

                case 6:
                    System.out.println("Enter username to remove:");
                    String removeUsername = scanner.nextLine();
                    userService.removeUser(removeUsername);
                    break;

                case 7:
                    System.out.println("Enter employee name:");
                    String empName = scanner.nextLine();
                    System.out.println("Enter employee ID:");
                    String empId = scanner.nextLine();
                    System.out.println("Enter salary:");
                    double salary = scanner.nextDouble();
                    scanner.nextLine(); // flush
                    System.out.println("Enter position:");
                    String position = scanner.nextLine();
                    employeeService.addEmployee(new Employee(empName, empId, salary, position));
                    break;

                case 8:
                    employeeService.displayEmployees();
                    break;

                case 9:
                    System.out.println("Enter employee ID to remove:");
                    String removeId = scanner.nextLine();
                    employeeService.removeEmployee(removeId);
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }

        } while (option != 0);

        scanner.close();
    }
}
