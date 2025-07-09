package main.model;

import java.util.ArrayList;
import java.util.List;

public class Bookstore {

    private String name;
    private List<Book> books;
    private List<Employee> employees;
    private List<User> users;

    public Bookstore(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    //Methods=================================

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bookstore Info\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Number of books: ").append(books.size()).append("\n");
        sb.append("Number of employees: ").append(employees.size()).append("\n");
        sb.append("Number of users: ").append(users.size());
        return sb.toString();
    }

    public void addBook(Book book) {
        for (Book m : books){
            if (m.getTitle().equals(book.getTitle()) && m.getAuthor().equals(book.getAuthor())){
                m.setQuantity(m.getQuantity() + 1);
                return;
            }
        }
        books.add(book);
    }

    public void displayBooks(){
        if (books.isEmpty()){
            System.out.println("No books available.");
        } else {
            System.out.println("List of books: ");
            for (Book book : books){
                System.out.println(book + "\n");
            }
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees available.");
        } else {
            System.out.println("List of employees:");
            for (Employee employee : employees) {
                System.out.println(employee + "\n");
            }
        }
    }

}
