package main.model;

public class Employee {

    private String name;
    private String id;
    private double salary;
    private String position;

    public Employee(String name, String id, double salary, String position) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    //Override========================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee\n");
        sb.append("Name:").append(name).append("\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Salary: ").append(salary);
        sb.append("Position: ").append(position).append("\n");
        return sb.toString();
    }
}
