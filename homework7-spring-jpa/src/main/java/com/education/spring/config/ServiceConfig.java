package com.education.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@Configuration
public class ServiceConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

}
