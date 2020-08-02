package com.education.spring.dao;

import com.education.spring.domain.Author;
import com.education.spring.domain.Book;
import com.education.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentsDao {

    Optional<Comment> findById(int id);
    List<Comment> findByBookId(int bookId);
    List<Comment> findAll();
    Comment save(Comment comment);
    int updateById(int id, Comment comment);
    int deleteById(int id);
    int deleteByBookId(int id);

}

