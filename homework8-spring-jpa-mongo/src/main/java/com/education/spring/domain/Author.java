package com.education.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class Author {

    @Id
    private int id;

    @Field(name = "authorname")
    private String name;

    @Field(name = "lastname")
    private String lastName;

    @Field(name = "surname")
    private String surname;

}
