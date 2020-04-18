package com.education.spring.service;

import com.education.spring.data.AppFileReader;
import com.education.spring.domain.User;
import org.springframework.stereotype.Service;

@Service
public class AppServiceModel {

    private final AppFileReader appFileReader;
    private final User user;

    public AppServiceModel(AppFileReader appFileReader, User user) {
        this.appFileReader = appFileReader;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void addUserAnswer(String answer) {
        user.getClientAnswers().add(answer);
    }

    public void answerResolver() {
        int result = 0;
        for (int i = 0; i < appFileReader.getAnswers().size(); i++) {
            if (appFileReader.getAnswers().get(i).equals(user.getClientAnswers().get(i))) {
                result++;
            }
        }
        user.setTestResult(result);
    }
}
