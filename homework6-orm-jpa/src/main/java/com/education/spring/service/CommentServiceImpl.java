package com.education.spring.service;


import com.education.spring.dao.CommentsDao;
import com.education.spring.domain.Book;
import com.education.spring.domain.Comment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService, AppService {

    private final CommentsDao commentDao;
    private final BookService bookService;

    public CommentServiceImpl(CommentsDao commentDao, BookService bookService) {
        this.commentDao = commentDao;
        this.bookService = bookService;
    }

    public String insertCommentInfo(Book book, String bookComment) {
        Comment comment = new Comment(0, book, bookComment);
        commentDao.save(comment);
        return "Комментарий успешно сохранен!";
    }

    public Comment findCommentById(int id) {
        Optional<Comment> optionalComment = commentDao.findById(id);
        Comment comment = optionalComment.get();
        if (comment == null)
            return null;
        return comment;
    }

    public List<Comment> findCommentByBook(Book bookInfo) {
        Book book = bookService.findBook(bookInfo);
        return commentDao.findByBookId(book.getId());
    }

    public String updateCommentById(int id, String updateComment) {
        Comment comment = findCommentById(id);
        if (comment == null)
            return "Комментария нет в базе";
        comment.setComment(updateComment);
        if(commentDao.update(comment) != null)
            return String.format("Новый комментарий для книги: %s", comment.getComment());
        return "Комментарий для книги не изменен!";
    }

    public String deleteCommentById(int id) {
        Comment comment = findCommentById(id);
        if (comment == null)
            return "Комментария нет в базе";
        commentDao.deleteById(id);
        if (commentDao.findById(id) == null)
            return "Комментарий успешно удалён!";
        return "Комментарий не удалён!";
    }

    public List<Comment> getAllComments() {
        try {
            return commentDao.findAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
