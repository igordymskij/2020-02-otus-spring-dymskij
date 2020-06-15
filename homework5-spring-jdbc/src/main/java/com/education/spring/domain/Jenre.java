package com.education.spring.domain;

public class Jenre {

    private int id;
    private String jenre;

    public Jenre(int id, String genre) {
        this.id = id;
        this.jenre = genre;
    }

    public int getId() {
        return id;
    }

    public String getJenre() {
        return jenre;
    }

    @Override
    public String toString() {
        return "Jenre{" +
                "id=" + id +
                ", jenre='" + jenre + '\'' +
                '}';
    }
}
