package com.education.spring.controller;

import com.education.spring.domain.Author;
import com.education.spring.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@ShellComponent
public class AppClientByAuthors extends AbstractAppClient {

    private final JdbcService jdbcService;
    private final Scanner sr;

    @Autowired
    public AppClientByAuthors(JdbcService jdbcService, Scanner sr) {
        this.jdbcService = jdbcService;
        this.sr = sr;
    }

    @ShellMethod(value = "Write info about author command", key = {"war", "writeAuthor"})
    public Map<String, String> writeInfoByAuthor() {
        String authorLastName = checkWriteClientData(WRITE_AUTHOR_LASTNAME, sr);
        String authorName = checkWriteClientData(WRITE_AUTHOR_NAME, sr);
        System.out.print(WRITE_AUTHOR_SURNAME);
        String authorSurName = sr.nextLine();
        Map<String, String> bookInfo = Map.of("authorLastName", authorLastName,
                                              "authorName", authorName,
                                              "authorSurName", authorSurName);
        jdbcService.insertAuthorInfo(bookInfo);
        return bookInfo;
    }

    private String writePointAuthorMenuInfo() {
        String point;
        System.out.println("Menu");
        System.out.println("1-Изменение фамилии автора");
        System.out.println("2-Изменение имени автора");
        System.out.println("3-Изменение отчества автора");
        do {
            System.out.print("Введите номер: ");
            point = sr.nextLine();
        } while (!checkUserAnswer(point));
        return point;
    }

    @ShellMethod(value = "Update info by author command", key = {"uar", "updateAuthor"})
    public String updateInfoByAuthor() {
        System.out.print(WRITE_AUTHOR_LASTNAME);
        String authorLastName = sr.nextLine();
        if(!checkWriteAuthorName(authorLastName))
            return "Данный автор отсуствует в базе!";
        switch (writePointAuthorMenuInfo()) {
            case "1": {
                System.out.print(WRITE_NEW_AUTHOR_LASTNAME);
                return jdbcService.updateAuthorLastName(authorLastName, sr.nextLine());
            }
            case "2":{
                System.out.print(WRITE_NEW_AUTHOR_NAME);
                return jdbcService.updateAuthorName(authorLastName, sr.nextLine());
            }
            case "3": {
                System.out.print(WRITE_AUTHOR_SURNAME);
                return jdbcService.updateAuthorSurname(authorLastName, sr.nextLine());
            }

        }
        return "Некорректный ввод!";
    }

    @ShellMethod(value = "Receive info about author command", key = {"gar", "getAuthor"})
    public String receiveAuthorInfoByName() {
        System.out.print(WRITE_AUTHOR_LASTNAME);
        String lastName = sr.nextLine();
        Author author = jdbcService.getAuthorByName(lastName);
        if (author == null)
            return "Данного автора нет в базе!";
        return author.toString();
    }

    @ShellMethod(value = "Receive info about all author command", key = {"gars", "getAuthors"})
    public List<Author> receiveAllAuthorsInfoByName() {
        return jdbcService.getAllAuthors();
    }

    @ShellMethod(value = "Delete info about author command", key = {"dar", "deleteAuthor"})
    public String deleteAuthorInfoByName() {
        System.out.print(WRITE_AUTHOR_LASTNAME);
        String lastName = sr.nextLine();
        return jdbcService.deleteAuthorByName(lastName);
    }

    private boolean checkWriteAuthorName(String authorName) {
        if(jdbcService.getAuthorByName(authorName) == null) {
            return false;
        }
        return true;
    }

}
