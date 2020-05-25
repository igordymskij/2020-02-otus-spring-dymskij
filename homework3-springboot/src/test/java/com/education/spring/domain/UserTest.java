package com.education.spring.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UserTest {

    @Test
    public void shouldHaveCorrectConstructor() {
        User user = new User();

        assertEquals(new ArrayList<>(), user.getUserAnswers());
    }

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

    @Test
    public void shouldHaveCorrectaddAnswerUser() {
        User user = new User();
        List<String> listAnswers = Arrays.asList("1","2","3","4","1");
        user.addClientAnswers(listAnswers);

        assertEquals(listAnswers, user.getUserAnswers());
    }

}
