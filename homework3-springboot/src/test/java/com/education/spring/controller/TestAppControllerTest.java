package com.education.spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAppControllerTest {

    private final String LOCAL_HOST = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TestAppController appController;


    @Test
    public void shouldHaveCorrectGetHelloFormOnRu() {
        ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
                  LOCAL_HOST + this.port + "/", String.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody(), "body not null");
        assertTrue(entity.getBody().contains("Уважаемый пользователь заполните форм и нажмите кнопку отправить"));
        assertTrue(entity.getBody().contains("Имя"));
        assertTrue(entity.getBody().contains("Фамилия"));
        assertTrue(entity.getBody().contains("русский"));
        assertTrue(entity.getBody().contains("english"));
        assertTrue(entity.getBody().contains("Отправить"));
    }

    @Test
    public void shouldHaveCorrectGetTestFormOnRu() throws Exception {
        ResponseEntity<String> entity = this.testRestTemplate.postForEntity(LOCAL_HOST + this.port + "/form?personName=Павел&personLastName=Дуров&choiceLanguage=ru", Object.class, String.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody(), "body not null");
        assertTrue(entity.getBody().contains("Павел Дуров, Вам предстоит пройти тест из 5 вопросов. Выберите вариант ответа в диапазоне [1-4] и нажмите кнопку Отправить!"));
        assertTrue(entity.getBody().contains("Вопрос №1 Какой из этих предметов является инструментом симфонического оркестра?"));
        assertTrue(entity.getBody().contains("1. Ножи"));
        assertTrue(entity.getBody().contains("2. Вилки"));
        assertTrue(entity.getBody().contains("3. Тарелки"));
        assertTrue(entity.getBody().contains("4. Стаканы"));
        assertTrue(entity.getBody().contains("Отправить"));
    }

    @Test
    public void shouldHaveCorrectGetTestFormOnEng() throws Exception {
        ResponseEntity<String> entity = this.testRestTemplate.postForEntity(LOCAL_HOST + this.port + "/form?personName=Elon&personLastName=Musk&choiceLanguage=eng", Object.class, String.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody(), "body not null");
        assertTrue(entity.getBody().contains("Elon Musk, you have to pass a test of 5 questions. Select an answer option in the range [1-4] and press button Send!"));
        assertTrue(entity.getBody().contains("Question №1 Which of these items is the instrument of the symphony orchestra?"));
        assertTrue(entity.getBody().contains("1. Knives"));
        assertTrue(entity.getBody().contains("2. Forks"));
        assertTrue(entity.getBody().contains("3. Plates"));
        assertTrue(entity.getBody().contains("4. Glasses"));
        assertTrue(entity.getBody().contains("Send"));
    }

    @Test
    public void shouldHaveCorrectGetTestResultForm() {
        ResponseEntity<String> entity = this.testRestTemplate.postForEntity(LOCAL_HOST + this.port + "/answer?0=3&1=3&2=4&3=2&4=1", Object.class, String.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody(), "body not null");
        assertTrue(entity.getBody().contains("Elon Musk, Your test result 5 of 5 correct answers!"));

    }

}
