package main.service;

import main.model.Employee;

public interface EmployeeOperations {
    void addEmployee(Employee employee);
    void displayEmployees();
    void removeEmployee(String id);
}
