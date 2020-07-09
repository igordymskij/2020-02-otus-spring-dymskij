package com.education.spring.controller;

import com.education.spring.domain.Jenre;
import com.education.spring.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class AppClientByJenres extends AbstractAppClient {

    private final JdbcService jdbcService;
    private final Scanner sr;

    @Autowired
    public AppClientByJenres(JdbcService jdbcService, Scanner sr) {
        this.jdbcService = jdbcService;
        this.sr = sr;
    }


    @ShellMethod(value = "Write info about jenre command", key = {"wje", "writeJenre"})
    public Jenre writeInfoByJenre() {
        String jenre = checkWriteClientData(WRITE_BOOK_JENRE, sr);
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

    private boolean checkWriteJenreName(String jenre) {
        if(jdbcService.getJenreByName(jenre) == null) {
            return false;
        }
        return true;
    }

}
