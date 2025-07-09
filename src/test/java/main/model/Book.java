package main.model;

public class Book {

    private String title;
    private String author;
    private int year;
    private double price;
    private String genre;

    public Book(String title, String author, int year, double price, String genre) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book\n");
        sb.append("Title: ").append(title).append('\n');
        sb.append("Author: ").append(author).append('\n');
        sb.append("Year: ").append(year);
        sb.append("Price: ").append(price);
        sb.append("Genre: ").append(genre).append('\n');
        return sb.toString();
    }
}
