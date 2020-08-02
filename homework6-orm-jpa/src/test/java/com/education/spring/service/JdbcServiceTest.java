package com.education.spring.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Import(JpaService.class)
class JdbcServiceTest {

//    @Autowired
//    private JdbcService jdbcService;
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void insertBookInfo() {
//        Book expectedBook = new Book("3", "Приключения Шелока Холмса и Доктора Ватсона",
//                new Author("4", "Артур", "Дойль", "Конан"),
//                new Jenre("3", "Детектив"),
//                "1997");
//        Map<String, String> bookInfo = Map.of("bookName", expectedBook.getName(),
//                "authorLastName", expectedBook.getAuthor().getLastName(),
//                "authorName", expectedBook.getAuthor().getName(),
//                "authorSurName", expectedBook.getAuthor().getSurname(),
//                "bookJenre", expectedBook.getJenre().getJenre(),
//                "bookYear", expectedBook.getYear());
//        Book actualBook = jdbcService.insertBookInfo(bookInfo);
//        assertThat(actualBook.toString()).isEqualTo(expectedBook.toString());
//
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void updateBookName() {
//        String actual = jdbcService.updateBookName("Приключения Алисы в Стране чудес", "Алиса в стране чудес");
//        assertThat(actual).isEqualTo("Новое название книги: Алиса в стране чудес");
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void updateBookAuthorName() {
//        String actual = jdbcService.updateBookAuthorName("Приключения Алисы в Стране чудес", "Роулинг");
//        assertThat(actual).isEqualTo("Новый автор книги: Джоан Роулинг");
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void updateBookJenreName() {
//        String actual = jdbcService.updateBookJenreName("Приключения Алисы в Стране чудес", "Фантастика");
//        assertThat(actual).isEqualTo("Новый жанр книги: Фантастика");
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void updateBookYear() {
//        String actual = jdbcService.updateBookYear("Приключения Алисы в Стране чудес", "2000");
//        assertThat(actual).isEqualTo("Изменённый год выпуска книги: 2000");
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void getBookByName() {
//        Book expectedBook = new Book("2", "Приключения Алисы в Стране чудес",
//                new Author("2", "Льюис", "Керролл", "null"),
//                new Jenre("1", "Приключения"),
//                "1865");
//        Book actualBook = jdbcService.getBookByName("Приключения Алисы в Стране чудес");
//        assertThat(actualBook.toString()).isEqualTo(expectedBook.toString());
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void deleteBookByName() {
//        Book book2 = new Book("2", "Приключения Алисы в Стране чудес",
//                new Author("2", "Льюис", "Керролл", null),
//                new Jenre("1", "Приключения"),
//                "1865");
//        List<Book> expectedBooks = Arrays.asList(book2);
//        jdbcService.deleteBookByName("Маленький принц");
//        List<Book> actualBooks = jdbcService.getAllBooks();
//        assertThat(actualBooks.toString()).isEqualTo(expectedBooks.toString());
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void getAllBooks() {
//        Book book1 = new Book("1", "Маленький принц",
//                new Author("1", "Антуан", "Сент-Экзюпери", null),
//                new Jenre("1", "Приключения"),
//                "1942");
//        Book book2 = new Book("2", "Приключения Алисы в Стране чудес",
//                new Author("2", "Льюис", "Керролл", null),
//                new Jenre("1", "Приключения"),
//                "1865");
//        List<Book> expectedBooks = Arrays.asList(book1, book2);
//        List<Book> actualBooks = jdbcService.getAllBooks();
//        assertThat(actualBooks.toString()).isEqualTo(expectedBooks.toString());
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void insertAuthorInfo() {
//        Author expectedAuthor = new Author("4", "Айзек", "Азимов", "");
//        Map<String, String> authorInfo = Map.of("authorLastName", expectedAuthor.getLastName(),
//                "authorName", expectedAuthor.getName(),
//                "authorSurName", expectedAuthor.getSurname());
//        jdbcService.insertAuthorInfo(authorInfo);
//        Author actualAuthor = jdbcService.getAuthorByName("Азимов");
//        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void getAuthorByName() {
//        Author expectedAuthor = new Author("1", "Антуан", "Сент-Экзюпери", null);
//        Author actualAuthor = jdbcService.getAuthorByName("Сент-Экзюпери");
//        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void updateAuthorLastName() {
//        Author expectedAuthor = new Author("1", "Льюис", "Сент-Экзюпери", null);
//        jdbcService.updateAuthorName("Сент-Экзюпери", "Льюис");
//        Author actualAuthor = jdbcService.getAuthorByName("Сент-Экзюпери");
//        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void updateAuthorName() {
//        Author expectedAuthor = new Author("1", "Антуан", "Холмс", null);
//        jdbcService.updateAuthorLastName("Сент-Экзюпери", "Холмс");
//        Author actualAuthor = jdbcService.getAuthorByName("Холмс");
//        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void updateAuthorSurname() {
//        Author expectedAuthor = new Author("1", "Антуан", "Сент-Экзюпери", "Мари");
//        jdbcService.updateAuthorSurname("Сент-Экзюпери", "Мари");
//        Author actualAuthor = jdbcService.getAuthorByName("Сент-Экзюпери");
//        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void deleteAuthorByName() {
//        Author author2 = new Author("2", "Льюис", "Керролл", null);
//        Author author3 = new Author("3", "Джоан", "Роулинг", null);
//        List<Author> expectedAuthors = Arrays.asList(author2, author3);
//        jdbcService.deleteAuthorByName("Сент-Экзюпери");
//        List<Author> actualAuthors = jdbcService.getAllAuthors();
//        assertThat(actualAuthors.toString()).isEqualTo(expectedAuthors.toString());
//
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void getAllAuthors() {
//        Author author1 = new Author("1", "Антуан", "Сент-Экзюпери", null);
//        Author author2 = new Author("2", "Льюис", "Керролл", null);
//        Author author3 = new Author("3", "Джоан", "Роулинг", null);
//        List<Author> expectedAuthors = Arrays.asList(author1, author2, author3);
//        List<Author> actualAuthors = jdbcService.getAllAuthors();
//        assertThat(actualAuthors.toString()).isEqualTo(expectedAuthors.toString());
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void insertJenreInfo() {
//        Jenre expectedJenre = new Jenre("3", "Детектив");
//        jdbcService.insertJenreInfo(expectedJenre.getJenre());
//        Jenre actualJenre = jdbcService.getJenreByName("Детектив");
//        assertThat(actualJenre).isEqualToComparingFieldByField(expectedJenre);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void getJenreByName() {
//        Jenre expectedJenre = new Jenre("1", "Приключения");
//        Jenre actualJenre = jdbcService.getJenreByName("Приключения");
//        assertThat(actualJenre).isEqualToComparingFieldByField(expectedJenre);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void updateJenre() {
//        Jenre expectedJenre = new Jenre("1", "Сказки");
//        jdbcService.updateJenre("Приключения", expectedJenre.getJenre());
//        Jenre actualJenre = jdbcService.getJenreByName("Сказки");
//        assertThat(actualJenre).isEqualToComparingFieldByField(expectedJenre);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void deleteJenreByName() {
//        Jenre jenre2 = new Jenre("2", "Фантастика");
//        List<Jenre> expectedJenres = Arrays.asList(jenre2);
//        jdbcService.deleteJenreByName("Приключения");
//        List<Jenre> actualJenres = jdbcService.getAllJenres();
//        assertThat(actualJenres.toString()).isEqualTo(expectedJenres.toString());
//    }

}