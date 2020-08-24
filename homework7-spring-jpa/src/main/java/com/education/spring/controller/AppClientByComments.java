package com.education.spring.controller;

import com.education.spring.domain.Book;
import com.education.spring.domain.Comment;
import com.education.spring.service.BookService;
import com.education.spring.service.CommentService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class AppClientByComments extends AppClient {

    private final CommentService commentService;
    private final BookService bookService;
    private final Scanner sr;

    public AppClientByComments(CommentService commentService, BookService bookService, Scanner sr) {
        this.commentService = commentService;
        this.bookService = bookService;
        this.sr = sr;
    }

    @ShellMethod(value = "Write comment about book command", key = {"wct", "writeComment"})
    public String writeInfoByComment() {
        Book bookInfo = writeBookInfo(sr);
        Book book = bookService.findBook(bookInfo);
        if (book == null)
            return "Данной книги нет в базе";
        String bookComment = checkWriteClientData(WRITE_BOOK_COMMENT, sr);
        String info = commentService.insertCommentInfo(book, bookComment);
        return String.format("%s Информация: %s", info, bookComment);
    }

    @ShellMethod(value = "Update comment by book command", key = {"uct", "updateComment"})
    public String updateInfoByComment() {
        String commentId = checkWriteClientData(WRITE_BOOK_COMMENT_ID, sr);
        System.out.print(WRITE_BOOK_NEW_COMMENT);
        return commentService.updateCommentById(Integer.valueOf(commentId), sr.nextLine());
    }

    @ShellMethod(value = "Receive comments about book command", key = {"gctsb", "getCommentsBook"})
    public String receiveCommentByBook() {
        Book book = writeBookInfo(sr);
        if(book == null)
            return "Данная книга отсуствует в базе!";
        List<Comment> comments = commentService.findCommentByBook(book);
        if (comments == null)
            return "По данной книге комментарии отсутствуют!";
        return comments.toString();
    }

    @ShellMethod(value = "Receive all comments about book command", key = {"gcts", "getComments"})
    public String receiveAllCommentByBookName() {
        List<Comment> comments = commentService.getAllComments();
        if (comments.size() == 0)
            return "По данной книге комментарии отсутствуют!";
        return comments.toString();
    }

}
