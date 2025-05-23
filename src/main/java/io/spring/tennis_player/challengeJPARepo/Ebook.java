package io.spring.tennis_player.challengeJPARepo;

import jakarta.persistence.*;

@Entity
@Table(name = "jpa_repo_ebook")
public class Ebook {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    @Column(name = "Publisher")
    private String author;
    private double price;

    // No-argument constructor
    public Ebook() {
    }

    // Parameterized constructor
    public Ebook(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    //constructor without Id attribute
    public Ebook(String title, String author, double price) {
        super();
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nEbook [" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ']';
    }
}