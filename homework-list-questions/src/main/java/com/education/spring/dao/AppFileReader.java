package com.education.spring.dao;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class AppFileReader {

    private String filePath;
    private final List<String[]> lines;
    private final List<String> questions;
    private final List<String> answers;

    public AppFileReader() {
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

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void fileReader() throws Exception {
        Reader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(filePath), "UTF-8"));
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
                if ((j+1)%6 == 0) {
                    answers.add(lines.get(i)[j].substring(7, 8));
                    continue;
                }
                questions.add(lines.get(i)[j]);
            }
        }
    }


}
