package com.education.spring.controller;

import com.education.spring.domain.Author;
import com.education.spring.service.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@ShellComponent
public class AppClientByAuthors extends AppClient {

    private final JpaService jpaService;
    private final Scanner sr;

    @Autowired
    public AppClientByAuthors(JpaService jpaService, Scanner sr) {
        this.jpaService = jpaService;
        this.sr = sr;
    }

    @ShellMethod(value = "Write info about author command", key = {"war", "writeAuthor"})
    public String writeInfoByAuthor() {
        Map<String, String> authorInfo = writeAuthorInfo(sr);
        String info = jpaService.insertAuthorInfo(authorInfo);
        return String.format("%s Информация: %s", info, authorInfo);
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
        Map<String, String> authorInfo = writeAuthorInfo(sr);
        if(!checkWriteAuthorName(authorInfo))
            return "Данный автор отсуствует в базе!";
        switch (writePointAuthorMenuInfo()) {
            case "1": {
                System.out.print(WRITE_NEW_AUTHOR_LASTNAME);
                return jpaService.updateAuthorLastName(authorInfo, sr.nextLine());
            }
            case "2":{
                System.out.print(WRITE_NEW_AUTHOR_NAME);
                return jpaService.updateAuthorName(authorInfo, sr.nextLine());
            }
            case "3": {
                System.out.print(WRITE_AUTHOR_SURNAME);
                return jpaService.updateAuthorSurname(authorInfo, sr.nextLine());
            }

        }
        return "Некорректный ввод!";
    }

    @ShellMethod(value = "Receive info about author command", key = {"gar", "getAuthor"})
    public String receiveAuthorInfoByName() {
        System.out.print(WRITE_AUTHOR_LASTNAME);
        String lastName = sr.nextLine();
        List<Author> author = jpaService.findAuthorByName(lastName);
        if (author == null)
            return "Данного автора нет в базе!";
        return author.toString();
    }

    @ShellMethod(value = "Receive info about all author command", key = {"gars", "getAuthors"})
    public List<Author> receiveAllAuthorsInfoByName() {
        return jpaService.getAllAuthors();
    }

    @ShellMethod(value = "Delete info about author command", key = {"dar", "deleteAuthor"})
    public String deleteAuthorInfoByName() {
        Map<String, String> authorInfo = writeAuthorInfo(sr);
        return jpaService.deleteAuthorByName(authorInfo);
    }

    private boolean checkWriteAuthorName(Map<String, String> authorName) {
        if(jpaService.findAuthor(authorName) == null) {
            return false;
        }
        return true;
    }

}
