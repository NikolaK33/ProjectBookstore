package main.service;

import main.model.Book;
import main.model.BookGenre;
import main.model.Bookstore;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class BookServiceTest {

    private Bookstore bookstore;
    private BookService bookService;

    @BeforeMethod
    public void setUp() {
        bookstore = new Bookstore("Test Bookstore");
        bookService = new BookService(bookstore);
    }

    @Test
    public void testAddBook() {
        Book book = new Book("Clean Code", "Robert Martin", 2008, 3000.0, BookGenre.NONFICTION, 1);
        bookService.addBook(book);

        List<Book> books = bookstore.getBooks();

        Assert.assertEquals(books.size(), 1);
        Assert.assertEquals(books.get(0).getTitle(), "Clean Code");
        Assert.assertEquals(books.get(0).getAuthor(), "Robert Martin");
    }

    @Test
    public void testRemoveBook() {
        Book book1 = new Book("Clean Code", "Robert Martin", 2008, 3000.0, BookGenre.NONFICTION, 1);
        Book book2 = new Book("Effective Java", "Joshua Bloch", 2018, 3500.0, BookGenre.NONFICTION, 1);

        bookService.addBook(book1);
        bookService.addBook(book2);

        bookService.removeBook("Clean Code");

        List<Book> books = bookstore.getBooks();

        Assert.assertEquals(books.size(), 1);
        Assert.assertEquals(books.getFirst().getTitle(), "Effective Java");
    }
}