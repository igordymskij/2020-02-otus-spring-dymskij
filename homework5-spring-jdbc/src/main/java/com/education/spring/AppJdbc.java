package com.education.spring;

import com.education.spring.dao.BooksDao;
import com.education.spring.domain.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties
public class AppJdbc {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AppJdbc.class, args);

        BooksDao dao = context.getBean(BooksDao.class);

        Book book;
        book = dao.getById(1);
        System.out.println(book);
        book = dao.getByName("Маленький принц");
        System.out.println(book);
        List<Book> books = dao.getAll();
        System.out.println(books);
        int id;
        id = dao.deleteById(1);
        System.out.println("deleteById=" + id);
        System.out.println(dao.getAll());
        id = dao.deleteByName("Приключения Алисы в Стране чудес");
        System.out.println("deleteByName=" + id);
        System.out.println(dao.getAll());

    }

}
