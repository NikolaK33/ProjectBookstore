package main.service;

import main.model.Bookstore;
import main.model.User;
import java.util.List;

public class UserService implements UserOperations {

    private Bookstore bookstore;

    public UserService(Bookstore bookstore) {
        this.bookstore = bookstore;
    }

    @Override
    public void addUser(User user) {
        bookstore.addUser(user);
    }

    @Override
    public void displayUsers() {
        bookstore.displayUsers();
    }

    @Override
    public void removeUser(String username) {
        List<User> users = bookstore.getUsers();
        boolean removed = users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
        if (removed) {
            System.out.println("User removed successfully.");
        } else {
            System.out.println("User not found.");
        }
    }
}
