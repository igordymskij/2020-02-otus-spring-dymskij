package com.education.spring.service;

import com.education.spring.data.AppFileReader;
import com.education.spring.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppServiceModelTest {

    @Mock
    private AppFileReader reader;

    private AppServiceModel model;


    @Test
    public void shouldHaveCorrectAnswerResoler() {
        when(reader.getAnswers()).thenReturn(Arrays.asList("3","3","4","2","1"));
        User user = new User();
        user.addClientAnswers(Arrays.asList("3","3","4","2","4"));
        model = new AppServiceModel(reader, user);
        model.answerResolver();

        assertEquals(4, model.getUser().getTestResult());
    }

    @Test
    public void shouldHaveCorrectAddUserAnswer() {
        User user = new User();
        model = new AppServiceModel(reader, user);
        model.addUserAnswer("4");

        assertEquals(Arrays.asList("4"), user.getClientAnswers());

    }
}