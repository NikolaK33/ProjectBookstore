package main.service;

import main.model.Book;
import main.model.Bookstore;

import java.util.List;

public class BookService implements BookOperations{

    private Bookstore bookstore;

    public BookService(Bookstore bookstore) {
        this.bookstore = bookstore;
    }

    @Override
    public void addBook(Book book) {
        LoggerService.log("Added book: " + book.getTitle() + " by " + book.getAuthor());
    }

    @Override
    public void displayBooks() {
        bookstore.displayBooks();
    }

    @Override
    public void removeBook(String title) {
        List<Book> books = bookstore.getBooks();
        boolean removed = books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }
}
