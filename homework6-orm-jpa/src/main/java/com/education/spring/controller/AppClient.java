package com.education.spring.controller;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppClient {

    final String WRITE_BOOK_NAME = "Введите название книги: ";
    final String WRITE_AUTHOR_LASTNAME = "Введите фамилию автора: ";
    final String WRITE_AUTHOR_NAME = "Введите имя автора: ";
    final String WRITE_AUTHOR_SURNAME = "Введите отчество автора: ";
    final String WRITE_BOOK_JENRE = "Введите жанр книги: ";
    final String WRITE_BOOK_YEAR = "Введите год выпуска книги: ";
    final String WRITE_BOOK_COMMENT = "Введите комментарий: ";
    final String WRITE_BOOK_NEW_COMMENT = "Введите новый комментарий книги: ";
    final String WRITE_BOOK_COMMENT_ID = "Введите ID комментария: ";

    final String WRITE_NEW_BOOK_NAME = "Введите новое название книги: ";
    final String WRITE_NEW_AUTHOR_LASTNAME = "Введите новую фамилию автора: ";
    final String WRITE_NEW_AUTHOR_NAME = "Введите новое имя автора: ";
    final String WRITE_NEW_AUTHOR_SURNAME = "Введите новое отчество автора: ";
    final String WRITE_NEW_BOOK_JENRE = "Введите новый жанр книги: ";
    final String WRITE_NEW_BOOK_YEAR = "Введите новый год выпуска книги: ";

    public Map<String, String> writeBookInfo(Scanner sr) {
        String bookName = checkWriteClientData(WRITE_BOOK_NAME, sr);
        String authorLastName = checkWriteClientData(WRITE_AUTHOR_LASTNAME, sr);
        String authorName = checkWriteClientData(WRITE_AUTHOR_NAME, sr);
        System.out.print(WRITE_AUTHOR_SURNAME);
        String authorSurname = sr.nextLine();
        String bookJenre = checkWriteClientData(WRITE_BOOK_JENRE, sr);
        String bookYear = checkWriteClientData(WRITE_BOOK_YEAR, sr);
        Map<String, String> bookInfo = Map.of("bookName", bookName,
                "authorLastName", authorLastName,
                "authorName", authorName,
                "authorSurname", authorSurname,
                "bookJenre", bookJenre,
                "bookYear", bookYear);
        return bookInfo;
    }

    public Map<String, String> writeAuthorInfo(Scanner sr) {
        String authorLastName = checkWriteClientData(WRITE_AUTHOR_LASTNAME, sr);
        String authorName = checkWriteClientData(WRITE_AUTHOR_NAME, sr);
        System.out.print(WRITE_AUTHOR_SURNAME);
        String authorSurName = sr.nextLine();
        Map<String, String> authorInfo = Map.of("authorLastName", authorLastName,
                "authorName", authorName,
                "authorSurname", authorSurName);
        return authorInfo;
    }

    public boolean checkUserAnswer(String answer) {
        Pattern p = Pattern.compile("[1-5]");
        Matcher m = p.matcher(answer);
        boolean checkResult = m.matches();
        if (checkResult == false)
            System.out.println("Некорректный ответ! Выберите вариант ответа в диапазоне [1-5]!");
        return checkResult;
    }

    public String checkWriteClientData(String warning, Scanner sr) {
        String data;
        do {
            System.out.print(warning);
            data = sr.nextLine();
        } while (data.isEmpty());
        return data;
    }

}
