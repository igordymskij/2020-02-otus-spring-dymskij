package com.education.spring.controller;

import com.education.spring.domain.User;
import com.education.spring.service.AppServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TestAppController {

    private AppServiceModel appServiceModel;

    @Autowired
    public TestAppController(AppServiceModel appServiceModel) {
        this.appServiceModel = appServiceModel;
    }

    @PostMapping("/form")
    public String postTest(@RequestParam(required=true) Map<String, String> userMap,
                                 Map<String, Object> model) {
        User user = appServiceModel.getUser();
        user.setClientName(userMap.get("personName"));
        user.setClientLastName(userMap.get("personLastName"));
        appServiceModel.fileReader(userMap.get("choiceLanguage"));
        model.putAll(appServiceModel.getAppRole());
        model.put("questions", appServiceModel.getAppFileReader().getQuestions());
        return "test";
    }

    @PostMapping("/answer")
    public String postAnswers(@RequestParam Map<String, String> map,
                           Map<String, Object> model) {
        model.put("userResult", appServiceModel.getUserTestResult(map));
        return "results";
    }

    @GetMapping("/")
    public String getTest() {
        return "userform";
    }

}
