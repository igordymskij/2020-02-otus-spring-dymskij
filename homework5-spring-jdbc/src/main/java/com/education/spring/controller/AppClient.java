package com.education.spring.controller;

import com.education.spring.domain.Author;
import com.education.spring.domain.Book;
import com.education.spring.domain.Jenre;
import com.education.spring.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ShellComponent
public class AppClient {

    private final JdbcService jdbcService;
    private final Scanner sr;

    private final String WRITE_BOOK_NAME = "Введите название книги: ";
    private final String WRITE_AUTHOR_LASTNAME = "Введите фамилию автора: ";
    private final String WRITE_AUTHOR_NAME = "Введите имя автора: ";
    private final String WRITE_AUTHOR_SURNAME = "Введите отчество автора: ";
    private final String WRITE_BOOK_JENRE = "Введите жанр книги: ";
    private final String WRITE_BOOK_YEAR = "Введите год выпуска книги: ";

    private final String WRITE_NEW_BOOK_NAME = "Введите новое название книги: ";
    private final String WRITE_NEW_AUTHOR_LASTNAME = "Введите новую фамилию автора: ";
    private final String WRITE_NEW_AUTHOR_NAME = "Введите новое имя автора: ";
    private final String WRITE_NEW_AUTHOR_SURNAME = "Введите новое отчество автора: ";
    private final String WRITE_NEW_BOOK_JENRE = "Введите новый жанр книги: ";
    private final String WRITE_NEW_BOOK_YEAR = "Введите новый год выпуска книги: ";

    @Autowired
    public AppClient(JdbcService jdbcService, Scanner sr) {
        this.jdbcService = jdbcService;
        this.sr = sr;
    }

    @ShellMethod(value = "Write info about book command", key = {"wbk", "writebook"})
    public Map<String, String> writeInfoByBook() {
        String bookName = checkWriteClientData(WRITE_BOOK_NAME);
        String authorLastName = checkWriteClientData(WRITE_AUTHOR_LASTNAME);
        String authorName = checkWriteClientData(WRITE_AUTHOR_NAME);
        System.out.print(WRITE_AUTHOR_SURNAME);
        String authorSurName = sr.nextLine();
        String bookJenre = checkWriteClientData(WRITE_BOOK_JENRE);;
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

    @ShellMethod(value = "Write info about author command", key = {"war", "writeAuthor"})
    public Map<String, String> writeInfoByAuthor() {
        String authorLastName = checkWriteClientData(WRITE_AUTHOR_LASTNAME);
        String authorName = checkWriteClientData(WRITE_AUTHOR_NAME);
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

    @ShellMethod(value = "Delete info about book command", key = {"dar", "deleteAuthor"})
    public String deleteAuthorInfoByName() {
        System.out.print(WRITE_AUTHOR_LASTNAME);
        String lastName = sr.nextLine();
        return jdbcService.deleteAuthorByName(lastName);
    }

    @ShellMethod(value = "Write info about jenre command", key = {"wje", "writeJenre"})
    public Jenre writeInfoByJenre() {
        String jenre = checkWriteClientData(WRITE_BOOK_JENRE);
        return jdbcService.insertJenreInfo(jenre);
    }

    @ShellMethod(value = "Update info by author command", key = {"uje", "updateJenre"})
    public String updateInfoByJenre() {
        System.out.print(WRITE_BOOK_JENRE);
        String jenre = sr.nextLine();
        if(!checkWriteJenreName(jenre))
            return "Данный жанр книги отсуствует в базе!";
        System.out.print(WRITE_NEW_BOOK_JENRE);
        return jdbcService.updateJenre(jenre, sr.nextLine());
    }

    @ShellMethod(value = "Receive info about jenre command", key = {"gje", "getJenre"})
    public String receiveJenreInfoByName() {
        System.out.print(WRITE_BOOK_JENRE);
        String jenreName = sr.nextLine();
        Jenre jenre = jdbcService.getJenreByName(jenreName);
        if (jenre == null)
            return "Данного автора нет в базе!";
        return jenre.toString();
    }

    @ShellMethod(value = "Receive info about all jenres command", key = {"gjes", "getGenres"})
    public String receiveAllJenresInfoByName() {
        List<Jenre> jenres = jdbcService.getAllJenres();
        if (jenres.size() == 0)
            return "Жанров в базе нет!";
        return jenres.toString();
    }

    @ShellMethod(value = "Delete info about jenre command", key = {"dje", "deleteJenre"})
    public String deleteJenreInfoByName() {
        System.out.print(WRITE_BOOK_JENRE);
        String jenre = sr.nextLine();
        return jdbcService.deleteJenreByName(jenre);
    }



        private String checkWriteClientData(String warning) {
        String data;
        do {
            System.out.print(warning);
            data = sr.nextLine();
        } while (data.isEmpty());
        return data;
    }

    private boolean checkUserAnswer(String answer) {
        Pattern p = Pattern.compile("[1-5]");
        Matcher m = p.matcher(answer);
        boolean checkResult = m.matches();
        if (checkResult == false)
            System.out.println("Некорректный ответ! Выберите вариант ответа в диапазоне [1-5]!");
        return checkResult;
    }

    private boolean checkWriteBookName(String bookName) {
        if(jdbcService.getBookByName(bookName) == null) {
            return false;
        }
        return true;
    }

    private boolean checkWriteAuthorName(String authorName) {
        if(jdbcService.getAuthorByName(authorName) == null) {
            return false;
        }
        return true;
    }

    private boolean checkWriteJenreName(String jenre) {
        if(jdbcService.getJenreByName(jenre) == null) {
            return false;
        }
        return true;
    }

}
