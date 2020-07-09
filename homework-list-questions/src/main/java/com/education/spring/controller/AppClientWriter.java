package com.education.spring.controller;

import com.education.spring.dao.AppFileReader;
import com.education.spring.service.AppServiceModel;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppClientWriter {

    private final AppServiceModel serviceModel;
    private final AppFileReader fileReader;

    public AppClientWriter(AppServiceModel serviceModel, AppFileReader fileReader) {
        this.serviceModel = serviceModel;
        this.fileReader = fileReader;
    }

    public void runTest () throws Exception {
        Scanner scan = new Scanner(System.in);
        fileReader.fileReader();
        inputUserName(scan);
        inputUserAnswer(scan);
        outputTestResult();
    }

    private void inputUserName(Scanner scan) {
        System.out.println("Уважаемый, пользователь, введите своё имя и фамилию и нажмите 'Enter'");
        System.out.print("Имя: ");
        serviceModel.setUserName(scan.nextLine());
        System.out.print("Фамилия: ");
        serviceModel.setUserLastName(scan.nextLine());
        System.out.println (serviceModel.getUser().getClientName() + " "
                + serviceModel.getUser().getClientLastName()
                + " , Вам предстоит пройти тест из "
                + fileReader.getQuestions().size()/5
                + " вопросов. Выберите вариант ответа в диапазоне [1-4] и нажмите 'Enter'");
    }

    private void inputUserAnswer(Scanner scan) {
        String answer;
        for (int i = 0; i < fileReader.getQuestions().size(); i++) {
            System.out.println(fileReader.getQuestions().get(i));
            if ((i+1)%5 == 0) {
                do {
                    System.out.print("Ваш ответ: ");
                    answer = scan.nextLine();
                } while (!checkClientAnswer(answer));
                serviceModel.getUser().getClientAnswers().add(answer);
            }
        }
    }

    private void outputTestResult() {
        serviceModel.answerResolver();
        System.out.println(serviceModel.getUser().getClientName() + " "
                + serviceModel.getUser().getClientLastName() + " , Ваш результат тестирования "
                + serviceModel.getUser().getTestResult() + " из 5 правильных ответов!");
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
