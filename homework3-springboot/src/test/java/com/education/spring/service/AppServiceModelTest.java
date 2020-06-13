package com.education.spring.service;

import com.education.spring.data.AppFileReader;
import com.education.spring.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AppServiceModelTest {

    @Mock
    private AppFileReader reader;

    @Mock
    private MessageSource messageSource;

    private AppServiceModel model;
    private User user;

    @Test
    public void shouldHaveCorrectAnswerResolver() {
        when(reader.getAnswers()).thenReturn(Arrays.asList("3","3","4","2","1"));
        when(messageSource.getMessage(any(), any(), any()))
                .thenReturn("Bellatrix Lestrange, Your test result 4 of 5 correct answers!");
        model = new AppServiceModel(reader, messageSource);
        Map<String, String> mapUserAnswers = Map.of("0", "3", "1", "3", "2", "4", "3","2", "4", "4");

        assertEquals("Bellatrix Lestrange, Your test result 4 of 5 correct answers!", model.getUserTestResult(mapUserAnswers));
    }

    @Test
    public void shouldHaveCorrectGetAppRoleOnRu() {
        when(reader.getQuestionsCount()).thenReturn(1);
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:messages/bundle");
        ms.setDefaultEncoding("UTF-8");
        model = new AppServiceModel(reader, ms);
        model.getUser().setClientName("Родион");
        model.getUser().setClientLastName("Роскольников");
        model.fileReader("ru");
        Map<String, String> map = Map.of("appRole", "Родион Роскольников, Вам предстоит пройти тест из 1 вопросов. Выберите вариант ответа в диапазоне [1-4] и нажмите кнопку Отправить!",
                "userAnswer", "Ваш ответ: ",
                "sendButton", "Отправить",
                "appFieldWarning", "Поля, отмеченные звёздочкой, являются обязательными для заполнения",
                "appHeader", "Тест");

        assertEquals(map, model.getAppRole());
    }

    @Test
    public void shouldHaveCorrectGetAppRoleOnEn() {
        when(reader.getQuestionsCount()).thenReturn(1);
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:messages/bundle");
        ms.setDefaultEncoding("UTF-8");
        model = new AppServiceModel(reader, ms);
        model.getUser().setClientName("Harry");
        model.getUser().setClientLastName("Potter");
        model.fileReader("eng");
        Map<String, String> map = Map.of("appRole", "Harry Potter, you have to pass a test of 1 questions. Select an answer option in the range [1-4] and press button Send!",
                "userAnswer", "Your answer: ",
                "sendButton", "Send",
                "appFieldWarning", "Fields marked with an asterisk are required",
                "appHeader", "Test");

        assertEquals(map, model.getAppRole());
    }
}