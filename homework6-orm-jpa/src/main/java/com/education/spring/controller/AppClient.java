package com.education.spring.controller;

import com.education.spring.domain.Author;
import com.education.spring.domain.Book;
import com.education.spring.domain.Jenre;

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

    public Book writeBookInfo(Scanner sr) {
        String bookName = checkWriteClientData(WRITE_BOOK_NAME, sr);
        String authorLastName = checkWriteClientData(WRITE_AUTHOR_LASTNAME, sr);
        String authorName = checkWriteClientData(WRITE_AUTHOR_NAME, sr);
        System.out.print(WRITE_AUTHOR_SURNAME);
        String authorSurname = sr.nextLine();
        String bookJenre = checkWriteClientData(WRITE_BOOK_JENRE, sr);
        String bookYear = checkWriteClientData(WRITE_BOOK_YEAR, sr);
        return new Book(0, bookName,
                new Author(0, authorName,
                        authorLastName,
                        authorSurname),
                new Jenre(0, bookJenre),
                bookYear);
    }

    public Author writeAuthorInfo(Scanner sr) {
        String authorLastName = checkWriteClientData(WRITE_AUTHOR_LASTNAME, sr);
        String authorName = checkWriteClientData(WRITE_AUTHOR_NAME, sr);
        System.out.print(WRITE_AUTHOR_SURNAME);
        String authorSurName = sr.nextLine();
        return new Author(0,
                authorName,
                authorLastName,
                authorSurName);
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
