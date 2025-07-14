package main.service;

import main.model.Bookstore;
import main.model.Employee;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class EmployeeServiceTest {

    private Bookstore bookstore;
    private EmployeeService employeeService;

    @BeforeMethod
    public void setUp() {
        bookstore = new Bookstore("Test Bookstore");
        employeeService = new EmployeeService(bookstore);
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee("Nikola", "E001", 50000, "Seller");
        employeeService.addEmployee(employee);

        List<Employee> employees = bookstore.getEmployees();

        Assert.assertEquals(employees.size(), 1);
        Assert.assertEquals(employees.get(0).getId(), "E001");
        Assert.assertEquals(employees.get(0).getPosition(), "Seller");
    }

    @Test
    public void testRemoveEmployee() {
        Employee e1 = new Employee("Nikola", "E001", 50000, "Seller");
        Employee e2 = new Employee("Milan", "E002", 60000, "Manager");

        employeeService.addEmployee(e1);
        employeeService.addEmployee(e2);

        employeeService.removeEmployee("E001");

        List<Employee> employees = bookstore.getEmployees();

        Assert.assertEquals(employees.size(), 1);
        Assert.assertEquals(employees.get(0).getId(), "E002");
    }
}
