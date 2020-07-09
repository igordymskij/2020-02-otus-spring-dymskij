package com.education.spring.controller;

import com.education.spring.domain.Book;
import com.education.spring.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@ShellComponent
public class AppClientByBooks extends AbstractAppClient {

    private final JdbcService jdbcService;
    private final Scanner sr;

    @Autowired
    public AppClientByBooks(JdbcService jdbcService, Scanner sr) {
        this.jdbcService = jdbcService;
        this.sr = sr;
    }

    @ShellMethod(value = "Write info about book command", key = {"wbk", "writebook"})
    public Map<String, String> writeInfoByBook() {
        String bookName = checkWriteClientData(WRITE_BOOK_NAME, sr);
        String authorLastName = checkWriteClientData(WRITE_AUTHOR_LASTNAME, sr);
        String authorName = checkWriteClientData(WRITE_AUTHOR_NAME, sr);
        System.out.print(WRITE_AUTHOR_SURNAME);
        String authorSurName = sr.nextLine();
        String bookJenre = checkWriteClientData(WRITE_BOOK_JENRE, sr);;
        System.out.print(WRITE_BOOK_YEAR);
        String bookYear = sr.nextLine();
        Map<String, String> bookInfo = Map.of("bookName", bookName,
                                            "authorLastName", authorLastName,
                                            "authorName", authorName,
                                            "authorSurName", authorSurName,
                                            "bookJenre", bookJenre,
                                            "bookYear", bookYear);
        jdbcService.insertBookInfo(bookInfo);
        return bookInfo;
    }

    private String writePointMenuInfo() {
        String point;
        System.out.println("Menu");
        System.out.println("1-Изменение название книги");
        System.out.println("2-Изменение имени автора книги");
        System.out.println("3-Изменение жанра книги");
        System.out.println("4-Изменение года выпуска книги");
        do {
            System.out.print("Введите номер: ");
            point = sr.nextLine();
        } while (!checkUserAnswer(point));
        return point;
    }

    @ShellMethod(value = "Update info by book command", key = {"ubk", "updateBook"})
    public String updateInfoByBook() {
        System.out.print(WRITE_BOOK_NAME);
        String bookName = sr.nextLine();
        if(!checkWriteBookName(bookName))
            return "Данная книга отсуствует в базе!";
        switch (writePointMenuInfo()) {
            case "1": {
                System.out.print(WRITE_NEW_BOOK_NAME);
                return jdbcService.updateBookName(bookName, sr.nextLine());
            }
            case "2":{
                System.out.print(WRITE_NEW_AUTHOR_LASTNAME);
                return jdbcService.updateBookAuthorName(bookName, sr.nextLine());
            }
            case "3": {
                System.out.print(WRITE_NEW_BOOK_JENRE);
                return jdbcService.updateBookJenreName(bookName, sr.nextLine());
            }
            case "4": {
                System.out.print(WRITE_NEW_BOOK_YEAR);
                return jdbcService.updateBookYear(bookName, sr.nextLine());
            }
        }
        return "Некорректный ввод!";
    }

    @ShellMethod(value = "Receive info about book command", key = {"gbk", "getBook"})
    public String receiveBookInfoByName() {
        System.out.print(WRITE_BOOK_NAME);
        String bookName = sr.nextLine();
        Book book = jdbcService.getBookByName(bookName);
        if (book == null)
            return "Данной книги нет в базе!";
        return book.toString();
    }

    @ShellMethod(value = "Receive info about all book command", key = {"gbks", "getBooks"})
    public List<Book> receiveAllBookInfoByName() {
        return jdbcService.getAllBooks();
    }

    @ShellMethod(value = "Delete info about book command", key = {"dbk", "deleteBook"})
    public String deleteBookInfoByName() {
        System.out.print(WRITE_BOOK_NAME);
        String bookName = sr.nextLine();
        return jdbcService.deleteBookByName(bookName);
    }

    private boolean checkWriteBookName(String bookName) {
        if(jdbcService.getBookByName(bookName) == null) {
            return false;
        }
        return true;
    }


}
