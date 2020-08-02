package com.education.spring.controller;

import com.education.spring.domain.Book;
import com.education.spring.domain.Comment;
import com.education.spring.service.JpaService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@ShellComponent
public class AppClientByComments extends AppClient {

    private final JpaService jpaService;
    private final Scanner sr;

    public AppClientByComments(JpaService jpaService, Scanner sr) {
        this.jpaService = jpaService;
        this.sr = sr;
    }

    @ShellMethod(value = "Write comment about book command", key = {"wct", "writeComment"})
    public String writeInfoByComment() {
        Map<String, String> bookInfo = writeBookInfo(sr);
        Book book = jpaService.findBook(bookInfo);
        if (book == null)
            return "Данной книги нет в базе";
        String bookComment = checkWriteClientData(WRITE_BOOK_COMMENT, sr);
        String info = jpaService.insertCommentInfo(book, bookComment);
        return String.format("%s Информация: %s", info, bookComment);
    }

    @ShellMethod(value = "Update comment by book command", key = {"uct", "updateComment"})
    public String updateInfoByComment() {
        String commentId = checkWriteClientData(WRITE_BOOK_COMMENT_ID, sr);
        System.out.print(WRITE_BOOK_NEW_COMMENT);
        return jpaService.updateCommentById(Integer.valueOf(commentId), sr.nextLine());
    }

    @ShellMethod(value = "Receive all comments about book command", key = {"gcts", "getComments"})
    public String receiveAllCommentByBookName() {
        List<Comment> comments = jpaService.getAllComments();
        if (comments.size() == 0)
            return "По данной книге комментарии отсутствуют!";
        return comments.toString();
    }

    @ShellMethod(value = "Delete all comments about book command", key = {"dcts", "deleteComments"})
    public String deleteCommentsByBookName() {
        Map<String, String> bookInfo = this.writeBookInfo(sr);
        return jpaService.deleteCommentByBookName(bookInfo);
    }
}
