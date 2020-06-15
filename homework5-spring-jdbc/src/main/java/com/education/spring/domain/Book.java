package com.education.spring.domain;

public class Book {

    private int id;
    private String name;
    private Author author;
    private Jenre jenre;
    private String year;

    public Book(int id, String name, Author author, Jenre jenre, String year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.jenre = jenre;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Jenre getJenre() {
        return jenre;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", jenre=" + jenre +
                ", year='" + year + '\'' +
                '}';
    }
}
