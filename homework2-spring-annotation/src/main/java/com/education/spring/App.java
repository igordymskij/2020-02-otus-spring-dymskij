package com.education.spring;

import com.education.spring.controller.AppClientWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        AppClientWriter appClientWriter = context.getBean(AppClientWriter.class);
        appClientWriter.runTest();
    }

}
