package com.education.spring.data;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class AppFileReader {

    private final List<String[]> lines;
    private final List<String> questions;
    private final List<String> answers;

    private final MessageSource messageSource;

    @Autowired
    public AppFileReader(MessageSource messageSource) {
        this.messageSource = messageSource;
        lines = new ArrayList<>();
        questions = new ArrayList<>();
        answers = new ArrayList<>();
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void fileReader(Locale locale) throws Exception {
        Reader reader = new BufferedReader(
            new InputStreamReader(getClass().getResourceAsStream(
                messageSource.getMessage("url.test", null, locale)), "UTF-8")
        );
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            lines.add(line);
        }
        reader.close();
        csvReader.close();
        linesSeparator();
    }

    private void linesSeparator() {
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length; j++) {
                if ((j + 1) % 6 == 0) {
                    answers.add(lines.get(i)[j].substring(lines.get(i)[j].length() - 1));
                    continue;
                }
                questions.add(lines.get(i)[j]);
            }
        }
    }

}