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
@Document(collection = "jenres")
public class Jenre {

    @Id
    private int id;

    @Field(name = "jenre")
    private String jenre;

}
