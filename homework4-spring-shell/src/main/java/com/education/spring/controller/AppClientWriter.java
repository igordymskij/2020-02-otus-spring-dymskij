package com.education.spring.controller;

import com.education.spring.data.AppFileReader;
import com.education.spring.service.AppServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ShellComponent
public class AppClientWriter {

    private final AppServiceModel serviceModel;
    private final AppFileReader fileReader;
    private final MessageSource messageSource;

    private Locale locale;
    private Scanner scan;

    public AppServiceModel getServiceModel() {
        return serviceModel;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    @Autowired
    public AppClientWriter(AppServiceModel serviceModel, AppFileReader fileReader, MessageSource messageSource, Scanner scan) {
        this.serviceModel = serviceModel;
        this.fileReader = fileReader;
        this.messageSource = messageSource;
        this.scan = scan;
    }

    @ShellMethod(value = "Write login user command", key = {"l", "log", "login"})
    @ShellMethodAvailability(value = "isLanguageCommandAvailable")
    public String inputUserName(@ShellOption(defaultValue = "Best") String name,
                              @ShellOption(defaultValue = "User") String lastName) {
        serviceModel.getUser().setClientName(name);
        serviceModel.getUser().setClientLastName(lastName);
        return getLocalMessage("user.hello", new String[]{name, lastName});
    }

    @ShellMethod(value = "Print test command", key = {"t", "test"})
    @ShellMethodAvailability(value = "isLoginCommandAvailable")
    public void inputUserAnswer() throws Exception {
        Scanner sc = scan;
        String answer;
        System.out.println (getLocalMessage("app.role",
                new String[]{
                        serviceModel.getUser().getClientName(),
                        serviceModel.getUser().getClientLastName()}
        ));
        fileReader.fileReader(locale);
        for (int i = 0; i < fileReader.getQuestions().size(); i++) {
            System.out.println(fileReader.getQuestions().get(i));
            if ((i+1)%5 == 0) {
                do {
                    System.out.print(getLocalMessage("user.answer", null));
                    answer = sc.nextLine();
                } while (!checkClientAnswer(answer));
                serviceModel.addUserAnswer(answer);
            }
        }
    }

    @ShellMethod(value = "Choice language command", key = {"lg", "lang", "language"})
    public String inputUserLanguage(@ShellOption(defaultValue = "RU") String language) {
        if (!checkClientLanguage(language)) {
            return "Допусимы только варианты 'RU' и 'ENG'";
        }
        switch (language) {
            case "RU": {
                locale = new Locale("ru", "RU");
                break;
            }
            case "ENG": {
                locale = new Locale("en", "US");
                break;
            }
        }
        return "Ваш выбор: " + language;
    }

    @ShellMethod(value = "Print result command", key = {"r", "res", "result"})
    @ShellMethodAvailability(value = "isTestResultCommandAvailable")
    public String outputTestResult() {
        serviceModel.answerResolver();
        return getLocalMessage("user.result",
            new String[]{
                serviceModel.getUser().getClientName(),
                serviceModel.getUser().getClientLastName(),
                String.valueOf(serviceModel.getUser().getTestResult())
            }
        );
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

    private Availability isLanguageCommandAvailable() {
        return locale == null
                ? Availability.unavailable("Сначала выберите язык/First choose your language!"): Availability.available();
    }

    private Availability isLoginCommandAvailable() {
        return serviceModel.getUser().getClientName() == null &&
                serviceModel.getUser().getClientLastName() == null
                ? Availability.unavailable(getLocalMessage("login.warning", null)): Availability.available();
    }

    private Availability isTestResultCommandAvailable() {
        return serviceModel.getUser().getClientAnswers().size() == 0
                ? Availability.unavailable(getLocalMessage("result.warning", null)): Availability.available();
    }

}
