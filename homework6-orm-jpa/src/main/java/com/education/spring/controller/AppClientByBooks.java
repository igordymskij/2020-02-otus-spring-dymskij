package com.education.spring.controller;


import com.education.spring.domain.Book;
import com.education.spring.service.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class AppClientByBooks extends AppClient {

    private final BookService bookService;
    private final Scanner sr;

    public AppClientByBooks(BookService bookService, Scanner sr) {
        this.bookService = bookService;
        this.sr = sr;
    }

    @ShellMethod(value = "Write info about book command", key = {"wbk", "writebook"})
    public String writeInfoByBook() {
        Book book = writeBookInfo(sr);
        String info = bookService.insertBookInfo(book);
        return String.format("%s Информация: %s", info, book);
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
        Book bookInfo = this.writeBookInfo(sr);
        Book book = checkWriteBookName(bookInfo);
        if(book == null)
            return "Данная книга отсуствует в базе!";
        switch (writePointMenuInfo()) {
            case "1": {
                System.out.print(WRITE_BOOK_NAME);
                return bookService.updateBookName(book, sr.nextLine());
            }
            case "2":{
                return bookService.updateBookAuthorName(book, writeAuthorInfo(sr));
            }
            case "3": {
                System.out.print(WRITE_NEW_BOOK_JENRE);
                return bookService.updateBookJenreName(book, sr.nextLine());
            }
            case "4": {
                System.out.print(WRITE_NEW_BOOK_YEAR);
                return bookService.updateBookYear(book, sr.nextLine());
            }
        }
        return "Некорректный ввод!";
    }

    @ShellMethod(value = "Receive info about book command", key = {"gbk", "getBook"})
    public String receiveBookInfoByName() {
        String bookName = checkWriteClientData(WRITE_BOOK_NAME, sr);
        List<Book> book = bookService.findBookByName(bookName);
        if (book == null)
            return "Данной книги нет в базе!";
        return book.toString();
    }

    @ShellMethod(value = "Receive info about all book command", key = {"gbks", "getBooks"})
    public String receiveAllBookInfoByName() {
        List<Book> bookList = bookService.getAllBooks();
        if (bookList.isEmpty())
            return "Список книг пуст!";
        return bookList.toString();
    }

    @ShellMethod(value = "Delete info about book command", key = {"dbk", "deleteBook"})
    public String deleteBookInfoByName() {
        Book book = this.writeBookInfo(sr);
        return bookService.deleteBookByName(book);
    }

    private Book checkWriteBookName(Book book) {
        return bookService.findBook(book);
    }


}
