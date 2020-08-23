package com.education.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jenres")
public class Jenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "jenre")
    private String jenre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jenre that = (Jenre) o;
        return jenre.equals(that.jenre);
    }

    @Override
    public int hashCode() {
        return jenre.hashCode();
    }

}
