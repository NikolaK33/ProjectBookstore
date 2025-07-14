package main.service;

import main.model.Bookstore;
import main.model.Employee;

import java.util.Scanner;

public class EmployeeUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void addEmployeeFromInput(Bookstore bookstore) {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter employee ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter position: ");
        String position = scanner.nextLine();

        Employee employee = new Employee(name, id, salary, position);
        bookstore.addEmployee(employee);
        LoggerService.log("Employee added: " + employee.getId());
    }

    public static void printAllEmployees(Bookstore bookstore) {
        System.out.println("\n--- All Employees ---");
        if (bookstore.getEmployees().isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : bookstore.getEmployees()) {
                System.out.println(employee);
            }
        }
    }
}