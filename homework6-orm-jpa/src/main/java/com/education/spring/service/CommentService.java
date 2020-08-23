package com.education.spring.service;

import com.education.spring.domain.Book;
import com.education.spring.domain.Comment;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    String insertCommentInfo(Book book, String bookComment);
    Comment findCommentById(int id);
    List<Comment> findCommentByBook(Book bookInfo);
    String updateCommentById(int id, String updateComment);
    String deleteCommentById(int id);
    List<Comment> getAllComments();
}
