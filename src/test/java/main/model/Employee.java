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

    //Methods========================================


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee\n");
        sb.append("name:").append(name).append('\'');
        sb.append(", id: ").append(id).append('\'');
        sb.append(", salary: ").append(salary);
        sb.append(", position: ").append(position).append('\'');
        return sb.toString();
    }
}
