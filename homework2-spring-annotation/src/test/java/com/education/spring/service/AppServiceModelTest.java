package com.education.spring.service;

import com.education.spring.data.AppFileReader;
import com.education.spring.domain.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AppServiceModelTest {

    @Mock
    private AppFileReader reader;

    @Mock
    private User user;

    AppServiceModel model;

    @BeforeEach
    private void setUp() {
        model = new AppServiceModel(reader, user);
    }

    @Test
    public void shouldHaveCorrectSetter() {
        User client = new User();
        given(model.getUser()).willReturn(client);

        assertEquals(client, model.getUser());
    }
}