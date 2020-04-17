package com.education.spring.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@DisplayName("Класс User")
public class UserTest {

    @DisplayName("корректо создаётся конструктором")
    @Test
    public void shouldHaveCorrectConstructor() {
        User user = new User();

        assertEquals(new ArrayList<>().size(), user.getClientAnswers().size());
    }

    @DisplayName("корректо изменяет свои поля")
    @Test
    public void shouldHaveCorrectSetter() {
        User user = new User();
        user.setClientName("Adam");
        user.setClientLastName("Sandler");
        user.setTestResult(5);

        assertEquals("Adam", user.getClientName());
        assertEquals("Sandler", user.getClientLastName());
        assertEquals(5, user.getTestResult());
    }

    @DisplayName("корректо добавляет ответы пользователя")
    @Test
    public void shouldHaveCorrectaddAnswerUser() {
        User user = new User();
        List<String> listAnswers = Arrays.asList("1","2","3","4","1");
        user.addClientAnswers(listAnswers);

        assertEquals(listAnswers, user.getClientAnswers());
    }

}
