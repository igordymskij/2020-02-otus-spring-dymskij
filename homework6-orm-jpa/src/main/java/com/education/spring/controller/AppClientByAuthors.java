package com.education.spring.controller;

import com.education.spring.domain.Author;
import com.education.spring.service.AuthorService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class AppClientByAuthors extends AppClient {

    private final AuthorService authorService;
    private final Scanner sr;

    public AppClientByAuthors(AuthorService authorService, Scanner sr) {
        this.authorService = authorService;
        this.sr = sr;
    }

    @ShellMethod(value = "Write info about author command", key = {"war", "writeAuthor"})
    public String writeInfoByAuthor() {
        Author author = writeAuthorInfo(sr);
        String info = authorService.insertAuthorInfo(author);
        return String.format("%s Информация: %s", info, author);
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
        Author author = writeAuthorInfo(sr);
        if(!checkWriteAuthorName(author))
            return "Данный автор отсуствует в базе!";
        switch (writePointAuthorMenuInfo()) {
            case "1": {
                System.out.print(WRITE_NEW_AUTHOR_LASTNAME);
                return authorService.updateAuthorLastName(author, sr.nextLine());
            }
            case "2":{
                System.out.print(WRITE_NEW_AUTHOR_NAME);
                return authorService.updateAuthorName(author, sr.nextLine());
            }
            case "3": {
                System.out.print(WRITE_AUTHOR_SURNAME);
                return authorService.updateAuthorSurname(author, sr.nextLine());
            }

        }
        return "Некорректный ввод!";
    }

    @ShellMethod(value = "Receive info about author command", key = {"gar", "getAuthor"})
    public String receiveAuthorInfoByName() {
        System.out.print(WRITE_AUTHOR_LASTNAME);
        String lastName = sr.nextLine();
        List<Author> author = authorService.findAuthorByName(lastName);
        if (author == null)
            return "Данного автора нет в базе!";
        return author.toString();
    }

    @ShellMethod(value = "Receive info about all author command", key = {"gars", "getAuthors"})
    public List<Author> receiveAllAuthorsInfoByName() {
        return authorService.getAllAuthors();
    }

    @ShellMethod(value = "Delete info about author command", key = {"dar", "deleteAuthor"})
    public String deleteAuthorInfoByName() {
        Author author = writeAuthorInfo(sr);
        return authorService.deleteAuthorByName(author);
    }

    private boolean checkWriteAuthorName(Author author) {
        if(authorService.findAuthor(author) == null) {
            return false;
        }
        return true;
    }

}
