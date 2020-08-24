package com.education.spring.repository;

import com.education.spring.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Integer>, CommentRepositoryCustom {

    Optional<Comment> findById(int id);
    List<Comment> findByBookId(int bookId);
    List<Comment> findAll();
    Comment save(Comment comment);
    void deleteById(int id);
}

