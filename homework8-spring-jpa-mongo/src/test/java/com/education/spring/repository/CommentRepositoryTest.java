package com.education.spring.repository;

import com.education.spring.domain.Author;
import com.education.spring.domain.Book;
import com.education.spring.domain.Comment;
import com.education.spring.domain.Jenre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findByIdTest() {
        Book book = new Book(0, "Маленький принц",
                new Author(0, "Антуан", "Сент-Экзюпери", null),
                new Jenre(0, "Приключения"),
                "1942");
        em.persist(book);
        Comment actualComment = new Comment(0, book, "Великая книга");
        em.persist(actualComment);
        Comment expectedComment = commentRepository.findById(1).get();
        assertThat(actualComment).isEqualToComparingFieldByField(expectedComment);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findAllTest() {
        Book book = new Book(0, "Маленький принц",
                new Author(0, "Антуан", "Сент-Экзюпери", null),
                new Jenre(0, "Приключения"),
                "1942");
        em.persist(book);
        Comment actualComment1 = new Comment(0, book, "Великая книга");
        Comment actualComment2 = new Comment(0, book, "Есть над чем задуматься");
        Comment actualComment3 = new Comment(0, book, "Детская книжечка непонятно о чем!!");
        em.persist(actualComment1);
        em.persist(actualComment2);
        em.persist(actualComment3);
        List<Comment> actualCommentsList = Arrays.asList(actualComment1, actualComment2, actualComment3);
        List<Comment> expectedCommentsList = commentRepository.findAll();
        assertThat(actualCommentsList).isEqualTo(expectedCommentsList);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void updateTest() {
        Book book = new Book(0, "Маленький принц",
                new Author(0, "Антуан", "Сент-Экзюпери", null),
                new Jenre(0, "Приключения"),
                "1942");
        em.persist(book);
        Comment actualComment = new Comment(0, book, "Великая книга");
        em.persist(actualComment);
        actualComment.setComment("Великая книга о жизни!");
        commentRepository.update(actualComment);
        em.refresh(actualComment);
        Comment expectedAuthor = commentRepository.findById(1).get();
        assertThat(actualComment).isEqualTo(expectedAuthor);
    }


    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void deleteByIdTest() {
        Book book = new Book(0, "Маленький принц",
                new Author(0, "Антуан", "Сент-Экзюпери", null),
                new Jenre(0, "Приключения"),
                "1942");
        em.persist(book);
        Comment actualComment1 = new Comment(0, book, "Великая книга");
        Comment actualComment2 = new Comment(0, book, "Есть над чем задуматься");
        List<Comment> actualCommentsList = Arrays.asList(actualComment2);
        em.persist(actualComment1);
        em.persist(actualComment2);
        commentRepository.deleteById(1);
        List<Comment> expectedCommentsList = commentRepository.findAll();
        assertThat(actualCommentsList).isEqualTo(expectedCommentsList);
    }
}