package com.education.spring.controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractAppClient {

    final String WRITE_BOOK_NAME = "Введите название книги: ";
    final String WRITE_AUTHOR_LASTNAME = "Введите фамилию автора: ";
    final String WRITE_AUTHOR_NAME = "Введите имя автора: ";
    final String WRITE_AUTHOR_SURNAME = "Введите отчество автора: ";
    final String WRITE_BOOK_JENRE = "Введите жанр книги: ";
    final String WRITE_BOOK_YEAR = "Введите год выпуска книги: ";

    final String WRITE_NEW_BOOK_NAME = "Введите новое название книги: ";
    final String WRITE_NEW_AUTHOR_LASTNAME = "Введите новую фамилию автора: ";
    final String WRITE_NEW_AUTHOR_NAME = "Введите новое имя автора: ";
    final String WRITE_NEW_AUTHOR_SURNAME = "Введите новое отчество автора: ";
    final String WRITE_NEW_BOOK_JENRE = "Введите новый жанр книги: ";
    final String WRITE_NEW_BOOK_YEAR = "Введите новый год выпуска книги: ";

    boolean checkUserAnswer(String answer) {
        Pattern p = Pattern.compile("[1-5]");
        Matcher m = p.matcher(answer);
        boolean checkResult = m.matches();
        if (checkResult == false)
            System.out.println("Некорректный ответ! Выберите вариант ответа в диапазоне [1-5]!");
        return checkResult;
    }

    String checkWriteClientData(String warning, Scanner sr) {
        String data;
        do {
            System.out.print(warning);
            data = sr.nextLine();
        } while (data.isEmpty());
        return data;
    }

}
