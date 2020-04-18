package com.education.spring;


import com.education.spring.controller.AppClientWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class AppTest 
{

    @Mock
    AppClientWriter appClientWriter;

    @Test
    public void shouldHaveCorrectStartApp() throws Exception {
        //TODO тут тоже не знаю как протестировать runTest()
    }
}
