package com.education.spring.data;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Arrays;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppFileReaderTest {

    @Mock
    private MessageSource messageSource;

    private AppFileReader reader;
    Locale locale;

    @Before
    public void setUp() {
        locale = new Locale("test", "Test");
    }

    @Test
    public void shouldHaveCorrectFileReaderForRu() throws Exception {
        when(messageSource.getMessage("url.test", null, locale)).thenReturn("/resource_test_ru.csv");
        reader = new AppFileReader(messageSource);
        reader.fileReader(locale);

        assertEquals(Arrays.asList("TestQuestion_RU",
                                    "TestVarible_1_RU",
                                    "TestVarible_2_RU",
                                    "TestVarible_3_RU",
                                    "TestVarible_4_RU"), reader.getQuestions());
        assertEquals(Arrays.asList("1"), reader.getAnswers());
    }

    @Test
    public void shouldHaveCorrectFileReaderForEng() throws Exception {
        when(messageSource.getMessage("url.test", null, locale)).thenReturn("/resource_test_en.csv");
        reader = new AppFileReader(messageSource);
        reader.fileReader(locale);

        assertEquals(Arrays.asList("TestQuestion_ENG",
                                    "TestVarible_1_ENG",
                                    "TestVarible_2_ENG",
                                    "TestVarible_3_ENG",
                                    "TestVarible_4_ENG"), reader.getQuestions());
        assertEquals(Arrays.asList("1"), reader.getAnswers());
    }
}
