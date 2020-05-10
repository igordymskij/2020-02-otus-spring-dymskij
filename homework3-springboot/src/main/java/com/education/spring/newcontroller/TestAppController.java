package com.education.spring.newcontroller;

import com.education.spring.data.AppFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Map;

@Controller
public class TestAppController {

    @Autowired
    private AppFileReader appFileReader;
//    private final MessageSource messageSource;

    private Locale locale;

    @PostMapping("/form")
    public String postTest(@RequestParam(name="personName", required=true) String name,
                                 @RequestParam(name="personLastName", required=true) String lastName,
                                 @RequestParam(name="choiceLanguage", required=true) String language,
                                 Map<String, Object> model) {
        System.out.println(name);
        System.out.println(lastName);
        System.out.println(language);
        Iterable<String> questions = fileReader();
        for (String str: questions) {
            System.out.println(str);
        }
        model.put("questions", questions);
        return "test";
    }

    @GetMapping("/")
    public String getTest() {
        return "userform";
    }

    private Iterable<String> fileReader() {
        try {
            appFileReader.fileReader(new Locale("ru", "RU"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appFileReader.getQuestions();
    }
}
