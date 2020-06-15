package com.education.spring.domain;

public class Author {

    private int id;
    private String name;
    private String lastName;
    private String surname;

    public Author(int id, String name, String lastName, String surname) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
