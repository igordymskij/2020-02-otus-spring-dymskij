package com.education.spring.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AppClientWriterTest {

    @MockBean
    Scanner scanner;

    @Autowired
    private AppClientWriter appClientWriter;

    @Autowired
    private Shell shell;

    private static final String COMMAND_LANGUAGE = "language";
    private static final String COMMAND_LANGUAGE_SHORT = "lg";
    private static final String COMMAND_LANGUAGE_STILL_SHORT = "lang";
    private static final String CUSTOM_LANGUAGE = "ENG";

    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_LOGIN_SHORT = "l";
    private static final String COMMAND_LOGIN_STILL_SHORT = "log";
    private static final String CUSTOM_LOGIN = "Новый Мир";

    private static final String COMMAND_TEST = "test";

    private static final String COMMAND_RESULT = "result";
    private static final String COMMAND_RESULT_SHORT = "res";
    private static final String COMMAND_RESULT_STILL_SHORT = "r";

    private static final String COMMAND_LOGIN_PATTERN = "%s %s";

    @Test
    void shouldReturnExpectedLanguage() {
        String res = (String) shell.evaluate(() -> COMMAND_LANGUAGE_SHORT);
        assertThat(res).isEqualTo("Ваш выбор: RU");

        res = (String) shell.evaluate(() -> COMMAND_LANGUAGE_STILL_SHORT);
        assertThat(res).isEqualTo("Ваш выбор: RU");

        res = (String) shell.evaluate(() -> COMMAND_LANGUAGE);
        assertThat(res).isEqualTo("Ваш выбор: RU");

        res = (String) shell.evaluate(() -> String.format(COMMAND_LOGIN_PATTERN, COMMAND_LANGUAGE, CUSTOM_LANGUAGE));
        System.out.println(res);
        assertThat(res).isEqualTo("Ваш выбор: ENG");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void shouldReturnExpectedLogin() {
        shell.evaluate(() -> COMMAND_LANGUAGE_SHORT);
        String res = (String) shell.evaluate(() -> COMMAND_LOGIN_SHORT);
        assertThat(res).isEqualTo("Добро пожаловать, Best User");

        res = (String) shell.evaluate(() -> COMMAND_LOGIN_STILL_SHORT);
        assertThat(res).isEqualTo("Добро пожаловать, Best User");

        res = (String) shell.evaluate(() -> COMMAND_LOGIN);
        assertThat(res).isEqualTo("Добро пожаловать, Best User");

        res = (String) shell.evaluate(() -> String.format(COMMAND_LOGIN_PATTERN, COMMAND_LOGIN, CUSTOM_LOGIN));
        assertThat(res).isEqualTo("Добро пожаловать, Новый Мир");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void shouldReturnExpectedResult() {
        appClientWriter.getServiceModel().getUser().setTestResult(1);
        Mockito.when(scanner.nextLine()).thenReturn("1");

        shell.evaluate(() -> COMMAND_LANGUAGE);
        shell.evaluate(() -> COMMAND_LOGIN);
        shell.evaluate(() -> COMMAND_TEST);

        String res = (String) shell.evaluate(() -> COMMAND_RESULT_SHORT);
        assertThat(res).isEqualTo("Best User, Ваш результат тестирования 1 из 5 правильных ответов!");

        res = (String) shell.evaluate(() -> COMMAND_RESULT_STILL_SHORT);
        assertThat(res).isEqualTo("Best User, Ваш результат тестирования 1 из 5 правильных ответов!");

        res = (String) shell.evaluate(() -> COMMAND_RESULT);
        assertThat(res).isEqualTo("Best User, Ваш результат тестирования 1 из 5 правильных ответов!");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void shouldReturnExpectedLanguageError() {
        Object res = shell.evaluate(() -> COMMAND_LOGIN_SHORT);
        assertThat(res).asString().contains("Сначала выберите язык/First choose your language!");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void shouldReturnExpectedTestError() {
        Object res = shell.evaluate(() -> COMMAND_TEST);
        assertThat(res).asString().contains("Вам нужно залогиниться!");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void shouldReturnExpectedResultError() {
        Object res = shell.evaluate(() -> COMMAND_RESULT);
        assertThat(res).asString().contains("Вы еще не прошли тест!");
    }

}
