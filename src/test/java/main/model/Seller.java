package main.model;

public class Seller extends Employee {

    public Seller(String name, String id, double salary, String position) {
        super(name, id, salary, position);
    }

    @Override
    public String toString() {
        return "Seller\n" + super.toString();
    }
}
