package com.education.spring.domain;

public class Jenre {

    private String id;
    private String jenre;

    public Jenre(String id, String genre) {
        this.id = id;
        this.jenre = genre;
    }

    public String getId() {
        return id;
    }

    public String getJenre() {
        return jenre;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Jenre{" +
                "id=" + id +
                ", jenre='" + jenre + '\'' +
                '}';
    }
}
