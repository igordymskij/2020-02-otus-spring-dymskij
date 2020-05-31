package com.education.spring.service;

import com.education.spring.data.AppFileReader;
import com.education.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppServiceModel {

    private final AppFileReader appFileReader;
    private final User user;
    private final MessageSource messageSource;

    private Locale locale;

    @Autowired
    public AppServiceModel(AppFileReader appFileReader, MessageSource messageSource) {
        this.appFileReader = appFileReader;
        this.user = new User();
        this.messageSource = messageSource;
    }

    public AppFileReader getAppFileReader() {
        return appFileReader;
    }

    public User getUser() {
        return user;
    }

    private void answerResolver() {
        int result = 0;
        for (int i = 0; i < appFileReader.getAnswers().size(); i++) {
            if (appFileReader.getAnswers().get(i).equals(user.getUserAnswers().get(i))) {
                result++;
            }
        }
        user.setTestResult(result);
    }

    public void fileReader(String language) {
        try {
            appFileReader.fileReader(choiceLanguage(language));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Locale choiceLanguage(String language) {
        switch (language) {
            case "ru": {
                locale = new Locale("ru", "RU");
                break;
            }
            case "eng": {
                locale = new Locale("en", "US");
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + language);
        }
        return locale;
    }

    public Map<String, String> getAppRole() {
        Map<String, String> map = new HashMap<>();
        map.put("appHeader", getLocalMessage("app.header", null));
        map.put("appRole", getLocalMessage("app.role",
            new String[]{
                user.getClientName(),
                user.getClientLastName(),
                String.valueOf(appFileReader.getQuestionsCount())}));
        map.put("userAnswer", getLocalMessage("user.answer", null));
        map.put("sendButton", getLocalMessage("button.send", null));
        map.put("appFieldWarning", getLocalMessage("app.field.warning", null));
        return map;
    }

    private String getLocalMessage(String bundleTag, String[] args) {
        return messageSource.getMessage(bundleTag, args, locale);
    }

    public String getUserTestResult(Map<String, String> map) {
        List<String> answersClientList = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            answersClientList.add(map.get(String.valueOf(i)));
        }
        user.addClientAnswers(answersClientList);
        answerResolver();
        return getLocalMessage("user.result",
            new String[]{
                user.getClientName(),
                user.getClientLastName(),
                String.valueOf(user.getTestResult()),
                String.valueOf(appFileReader.getQuestionsCount())});
    }

}
