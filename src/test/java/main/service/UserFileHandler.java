package main.service;

import main.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileHandler {

    private static final String FILE_PATH = "users.txt";

    public static void saveUsers(List<User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.println(user.getName() + ";" +
                        user.getUsername() + ";" +
                        user.getPassword() + ";" +
                        user.getEmail());
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String name = parts[0];
                    String username = parts[1];
                    String password = parts[2];
                    String email = parts[3];

                    users.add(new User(name, username, password, email));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }

        return users;
    }
}