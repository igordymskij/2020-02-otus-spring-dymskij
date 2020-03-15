package com.education.spring.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppFileReader {

    private String filePath;
    private final List<String> lines = new ArrayList<>();
    private final List<String> questions = new ArrayList<>();
    private final List<String> answers = new ArrayList<>();

    public AppFileReader() {
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void fileReader() {
        //        Files.lines(Paths.get(filePath), StandardCharsets.UTF_8).forEach(System.out::println);
//        try {
//            lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
//            linesSeparator();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
