package com.education.spring.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppFileReader {

    private String filePath;
    private final List<String> lines;
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

    public void fileReader() {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            linesSeparator();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void linesSeparator() {
        for (int i = 0; i < lines.size(); i++) {
            if ((i+1)%6 == 0) {
                answers.add(lines.get(i).substring(7, 8));
                continue;
            }
            questions.add(lines.get(i));
        }
    }

}
