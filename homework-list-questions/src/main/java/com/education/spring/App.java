package com.education.spring;

import com.education.spring.controller.AppClientWriter;
import com.education.spring.data.AppFileReader;
import com.education.spring.data.User;
import com.education.spring.service.AppLogicsCare;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {

    public static void main( String[] args ) {
//        AppFileReader appFileReader = new AppFileReader();
//        appFileReader.fileReader();
//        User user = new User();
//        AppLogicsCare appLogicsCare = new AppLogicsCare(appFileReader, user);
//        AppClientWriter appClientWriter = new AppClientWriter(appLogicsCare);
//        appClientWriter.clientWriter();

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        AppClientWriter appClientWriter = context.getBean(AppClientWriter.class);
        appClientWriter.clientWriter();
    }

}
