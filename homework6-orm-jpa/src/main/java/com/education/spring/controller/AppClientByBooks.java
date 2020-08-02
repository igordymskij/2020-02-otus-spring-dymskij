package com.education.spring.controller;


import com.education.spring.domain.Book;
import com.education.spring.service.JpaService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@ShellComponent
public class AppClientByBooks extends AppClient {

    private final JpaService jpaService;
    private final Scanner sr;

    public AppClientByBooks(JpaService jpaService, Scanner sr) {
        this.jpaService = jpaService;
        this.sr = sr;
    }

    @ShellMethod(value = "Write info about book command", key = {"wbk", "writebook"})
    public String writeInfoByBook() {
        Map<String, String> bookInfo = writeBookInfo(sr);
        String info = jpaService.insertBookInfo(bookInfo);
        return String.format("%s Информация: %s", info, bookInfo);
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
        Map<String, String> bookInfo = this.writeBookInfo(sr);
        Book book = checkWriteBookName(bookInfo);
        if(book == null)
            return "Данная книга отсуствует в базе!";
        switch (writePointMenuInfo()) {
            case "1": {
                System.out.print(WRITE_BOOK_NAME);
                return jpaService.updateBookName(book, sr.nextLine());
            }
            case "2":{
                return jpaService.updateBookAuthorName(book, writeAuthorInfo(sr));
            }
            case "3": {
                System.out.print(WRITE_NEW_BOOK_JENRE);
                return jpaService.updateBookJenreName(book, sr.nextLine());
            }
            case "4": {
                System.out.print(WRITE_NEW_BOOK_YEAR);
                return jpaService.updateBookYear(book, sr.nextLine());
            }
        }
        return "Некорректный ввод!";
    }

    @ShellMethod(value = "Receive info about book command", key = {"gbk", "getBook"})
    public String receiveBookInfoByName() {
        String bookName = checkWriteClientData(WRITE_BOOK_NAME, sr);
        List<Book> book = jpaService.findBookByName(bookName);
        if (book == null)
            return "Данной книги нет в базе!";
        return book.toString();
    }

    @ShellMethod(value = "Receive info about all book command", key = {"gbks", "getBooks"})
    public String receiveAllBookInfoByName() {
        List<Book> bookList = jpaService.getAllBooks();
        if (bookList.isEmpty())
            return "Список книг пуст!";
        return bookList.toString();
    }

    @ShellMethod(value = "Delete info about book command", key = {"dbk", "deleteBook"})
    public String deleteBookInfoByName() {
        Map<String, String> bookInfo = this.writeBookInfo(sr);
        return jpaService.deleteBookByName(bookInfo);
    }

    private Book checkWriteBookName(Map<String, String> bookInfo) {
        return jpaService.findBook(bookInfo);
    }


}
