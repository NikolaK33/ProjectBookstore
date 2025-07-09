package main.service;

import main.model.Book;

public interface BookOperations {

    void addBook(Book book);
    void displayBooks();
    void removeBook(String title);
}
