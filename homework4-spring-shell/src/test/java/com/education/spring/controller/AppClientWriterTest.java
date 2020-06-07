package com.education.spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class AppClientWriterTest {

//    @MockBean
//    public AppServiceModel serviceModel;
//
//    @MockBean
//    private AppFileReader fileReader;
//
//    @MockBean
//    private MessageSource messageSource;

    @Autowired
    private AppClientWriter appClientWriter;

    @Autowired
    private Shell shell;

    private static final String COMMAND_LANGUAGE = "language";
    private static final String COMMAND_LANGUAGE_SHORT = "lang";
    private static final String COMMAND_LANGUAGE_STILL_SHORT = "l";
    private static final String CUSTOM_LANGUAGE = "ENG";

    private static final String COMMAND_LOGIN_PATTERN = "%s %s";

//    @BeforeEach
//    private void setUp() {
//        appClientWriter = new AppClientWriter(serviceModel, fileReader, messageSource);
//    }

    @Test
    void shouldReturnExpectedGreetingAfterLoginCommandEvaluated() {
        String res;
        res = (String) shell.evaluate(() -> COMMAND_LANGUAGE_SHORT);
        assertThat(res).isEqualTo("Ваш выбор: RU");

        res = (String) shell.evaluate(() -> COMMAND_LANGUAGE_STILL_SHORT);
        assertThat(res).isEqualTo("Ваш выбор: RU");

        res = (String) shell.evaluate(() -> String.format(COMMAND_LOGIN_PATTERN, COMMAND_LANGUAGE, CUSTOM_LANGUAGE));
        System.out.println(res);
        assertThat(res).isEqualTo("Ваш выбор: ENG");
    }

}
