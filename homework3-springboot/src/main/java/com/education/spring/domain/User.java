package com.education.spring.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class User {

    private String clientName;
    private String clientLastName;
    private List<String> clientAnswers;
    private int testResult;

    public User() {
        this.clientAnswers = new ArrayList<>();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public List<String> getUserAnswers() {
        return clientAnswers;
    }

    public void addClientAnswers(List<String> clientAnswers) {
        this.clientAnswers.clear();
        this.clientAnswers.addAll(clientAnswers);
    }

    public int getTestResult() {
        return testResult;
    }

    public void setTestResult(int testResult) {
        this.testResult = testResult;
    }
}
