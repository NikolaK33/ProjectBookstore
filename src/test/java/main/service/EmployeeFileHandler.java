package main.service;

import main.model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileHandler {

    private static final String FILE_PATH = "employees.txt";

    public static void saveEmployees(List<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Employee employee : employees) {
                writer.println(employee.getName() + ";" +
                        employee.getId() + ";" +
                        employee.getSalary() + ";" +
                        employee.getPosition());
            }
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }

    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) return employees;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String name = parts[0];
                    String id = parts[1];
                    double salary = Double.parseDouble(parts[2]);
                    String position = parts[3];

                    employees.add(new Employee(name, id, salary, position));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading employees: " + e.getMessage());
        }

        return employees;
    }
}