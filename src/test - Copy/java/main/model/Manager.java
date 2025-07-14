package main.model;

public class Manager extends Employee{

    private String department;

    public Manager(String name, String id, double salary, String position, String department) {
        super(name, id, salary, position);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    //Override================================================


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Manager\n");
        sb.append(super.toString()).append("\n");
        sb.append("Department: ").append(department).append("\n");
        return sb.toString();
    }
}
