package com.education.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AppJpa {

    public static void main(String[] args) {
            SpringApplication.run(AppJpa.class, args);
    }

}
