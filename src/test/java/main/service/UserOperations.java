package main.service;

import main.model.User;

public interface UserOperations {
    void addUser(User user);
    void displayUsers();
    void removeUser(String username);
}
