package main.service;

import main.model.Bookstore;
import main.model.User;

import java.util.Scanner;

public class UserUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void addUserFromInput(Bookstore bookstore) {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User user = new User(name, username, password, email);
        bookstore.addUser(user);
        LoggerService.log("User added: " + user.getUsername());
    }

    public static void printAllUsers(Bookstore bookstore) {
        System.out.println("\n=== Registered Users ===");
        if (bookstore.getUsers().isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : bookstore.getUsers()) {
                System.out.println(user);
            }
        }
    }
}