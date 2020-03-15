package com.education.spring.controller;

import com.education.spring.service.AppLogicsCare;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppClientWriter {

    private final AppLogicsCare appLogicsCare;

    public AppClientWriter(AppLogicsCare appLogicsCare) {
        this.appLogicsCare = appLogicsCare;
    }

    public void clientWriter() {
        String answer = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Уважаемый, пользователь, введите своё имя и фамилию и нажмите 'Enter'");
        System.out.print("Имя: ");
        appLogicsCare.getUser().setClientName(scan.nextLine());
        System.out.print("Фамилия: ");
        appLogicsCare.getUser().setClientLastName(scan.nextLine());
        System.out.println (appLogicsCare.getUser().getClientName() + " "
                + appLogicsCare.getUser().getClientLastName()
                + " , Вам предстоит пройти тест из "
                + appLogicsCare.getAppFileReader().getQuestions().size()/5
                + " вопросов. Выберите вариант ответа в диапазоне [1-4] и нажмите 'Enter'");

        for (int i = 0; i < appLogicsCare.getAppFileReader().getQuestions().size(); i++) {
            System.out.println(appLogicsCare.getAppFileReader().getQuestions().get(i));
            if ((i+1)%5 == 0) {
                do {
                    System.out.print("Ваш ответ: ");
                    answer = scan.nextLine();
                } while (!checkClientAnswer(answer));
                appLogicsCare.getUser().getClientAnswers().add(answer);
            }
        }

        appLogicsCare.answerResolver();

        System.out.println(appLogicsCare.getUser().getClientName() + " "
                + appLogicsCare.getUser().getClientLastName() + " , Ваш результат тестирования "
                + appLogicsCare.getUser().getTestResult() + " из 5 правильных ответов!");
    }


    private boolean checkClientAnswer(String answer) {
        Pattern p = Pattern.compile("[1-4]");
        Matcher m = p.matcher(answer);
        boolean checkResult = m.matches();
        if (checkResult == false)
            System.out.println("Некорректный ответ! Выберите вариант ответа в диапазоне [1-4]!");
        return checkResult;
    }
}
