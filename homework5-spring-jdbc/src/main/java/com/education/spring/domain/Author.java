package com.education.spring.domain;

public class Author {

    private String id;
    private String name;
    private String lastName;
    private String surname;

    public Author(String id, String name, String lastName, String surname) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.surname = surname;
    }

    public String getId() {
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

    public void setId(String id) {
        this.id = id;
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
