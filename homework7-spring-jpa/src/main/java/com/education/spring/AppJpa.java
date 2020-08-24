package com.education.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AppJpa {

    public static void main(String[] args) {
            SpringApplication.run(AppJpa.class, args);
    }

}
