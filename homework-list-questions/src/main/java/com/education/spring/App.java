package com.education.spring;

import com.education.spring.controller.AppClientWriter;
import com.education.spring.data.AppFileReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {

    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        AppClientWriter appClientWriter = context.getBean(AppClientWriter.class);
        AppFileReader appFileReader = context.getBean(AppFileReader.class);
        appFileReader.fileReader();
        appClientWriter.clientWriter();
    }

}
