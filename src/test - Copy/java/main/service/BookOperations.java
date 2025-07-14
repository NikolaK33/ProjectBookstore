package main.service;

import main.model.Book;
import main.model.BookGenre;
import main.model.Bookstore;

import java.util.Scanner;

public interface BookOperations {

    void addBook(Book book);
    void displayBooks();
    void removeBook(String title);
}
