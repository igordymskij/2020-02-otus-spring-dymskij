package com.education.spring.controller;

import com.education.spring.data.AppFileReader;
import com.education.spring.service.AppServiceModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
public class AppClientWriterTest {

    @MockBean
    private AppServiceModel serviceModel;

    @MockBean
    private AppFileReader fileReader;

    @MockBean
    private MessageSource messageSource;

    private AppClientWriter appClientWriter;

    @Autowired
    private Shell shell;

    private static final String COMMAND_LANGUAGE = "language";
    private static final String COMMAND_LANGUAGE_SHORT = "lang";
    private static final String COMMAND_LANGUAGE_STILL_SHORT = "lang";
    private static final String CUSTOM_LANGUAGE = "ENG";

    private static final String COMMAND_LOGIN_PATTERN = "%s %s";

    @Test
    void shouldReturnExpectedGreetingAfterLoginCommandEvaluated() {
        appClientWriter = new AppClientWriter(serviceModel, fileReader, messageSource);
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
