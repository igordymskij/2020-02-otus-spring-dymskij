package com.education.spring;

import com.education.spring.controller.AppClientWriter;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {

    public static void main( String[] args ) throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        AppClientWriter appClientWriter = context.getBean(AppClientWriter.class);
        appClientWriter.runTest();
    }

}
