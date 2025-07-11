package main.service;

import main.model.Bookstore;
import main.model.Employee;

import java.util.List;

public class EmployeeService implements EmployeeOperations {

    private Bookstore bookstore;

    public EmployeeService(Bookstore bookstore) {
        this.bookstore = bookstore;
    }

    @Override
    public void addEmployee(Employee employee) {
        bookstore.addEmployee(employee);
    }

    @Override
    public void displayEmployees() {
        bookstore.displayEmployees();
    }

    @Override
    public void removeEmployee(String id) {
        List<Employee> employees = bookstore.getEmployees();
        boolean removed = employees.removeIf(e -> e.getId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}