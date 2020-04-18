package com.education.spring.controller;

import com.education.spring.data.AppFileReader;
import com.education.spring.service.AppServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AppClientWriter {

    private final AppServiceModel serviceModel;
    private final AppFileReader fileReader;
    private final MessageSource messageSource;

    private Locale locale;
    private Scanner scan;

    @Autowired
    public AppClientWriter(AppServiceModel serviceModel, AppFileReader fileReader, MessageSource messageSource) {
        this.serviceModel = serviceModel;
        this.fileReader = fileReader;
        this.messageSource = messageSource;
    }

    public void runTest () throws Exception {
        scan = new Scanner(System.in);
        inputUserLanguage(scan);
        inputUserName(scan);
        inputUserAnswer(scan);
        fileReader.fileReader(locale);
        inputUserAnswer(scan);
        outputTestResult();
    }

    private void inputUserName(Scanner scan) {
        System.out.println(getLocalMessage("user.hello", null));
        System.out.print(getLocalMessage("user.name", null));
        serviceModel.getUser().setClientName(scan.nextLine());
        System.out.print(getLocalMessage("user.lastname", null));
        serviceModel.getUser().setClientLastName(scan.nextLine());
        System.out.println (getLocalMessage("app.role",
            new String[]{
                serviceModel.getUser().getClientName(),
                serviceModel.getUser().getClientLastName(),
                String.valueOf(fileReader.getQuestions().size()/5)}
        ));
    }

    private void inputUserAnswer(Scanner scan) {
        String answer;
        for (int i = 0; i < fileReader.getQuestions().size(); i++) {
            System.out.println(fileReader.getQuestions().get(i));
            if ((i+1)%5 == 0) {
                do {
                    System.out.print(getLocalMessage("user.answer", null));
                    answer = scan.nextLine();
                } while (!checkClientAnswer(answer));
                serviceModel.addUserAnswer(answer);
            }
        }
    }

    private void inputUserLanguage(Scanner scan) {
        String userLanguage;
        System.out.println("Выберите язык: RU или ENG и нажмите 'Enter'");
        do {
            System.out.print("Ваш выбор: ");
            userLanguage = scan.nextLine();
        } while (!checkClientLanguage(userLanguage));
        switch (userLanguage) {
            case "RU": {
                locale = new Locale("ru", "RU");
                break;
            }
            case "ENG": {
                locale = new Locale("en", "US");
                break;
            }
        }
    }

    private void outputTestResult() {
        serviceModel.answerResolver();
        System.out.println(getLocalMessage("user.result",
            new String[]{
                serviceModel.getUser().getClientName(),
                serviceModel.getUser().getClientLastName(),
                String.valueOf(serviceModel.getUser().getTestResult())
            }
        ));
    }

    private boolean checkClientAnswer(String answer) {
        Pattern p = Pattern.compile("[1-4]");
        Matcher m = p.matcher(answer);
        boolean checkResult = m.matches();
        if (checkResult == false)
            System.out.println(getLocalMessage("app.warning", null));
        return checkResult;
    }

    private boolean checkClientLanguage(String userLanguage) {
        Pattern p = Pattern.compile("RU|ENG");
        Matcher m = p.matcher(userLanguage);
        boolean checkResult = m.matches();
        return checkResult;
    }

    private String getLocalMessage(String bundleTag, String[] args) {
        return messageSource.getMessage(bundleTag, args, locale);
    }

}
