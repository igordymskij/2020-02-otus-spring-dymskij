package com.education.spring;


import com.education.spring.controller.AppClientWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class AppTest 
{

    @MockBean
    AppClientWriter appClientWriter;

    @Test
    public void shouldHaveCorrectStartApp() throws Exception {
        //TODO тут тоже не знаю как протестировать runTest()
    }
}
