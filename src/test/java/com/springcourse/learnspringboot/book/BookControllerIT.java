package com.springcourse.learnspringboot.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort // injects the HTTP server port that was generated at runtime
    private int randomServerPort;

    @Test
    void shouldReturnAllBooks() {
        Book[] books = restTemplate.getForObject("http://localhost:" + randomServerPort + "/books", Book[].class);

        assertEquals(3, books.length);
        assertEquals("ISBN1", books[0].getIsbn());
    }

    @Test
    void shouldReturnBookById() {
        Book book = restTemplate.getForObject("http://localhost:" + randomServerPort + "/books/1003", Book.class);

        assertEquals(1003, book.getId());
        assertEquals("ISBN3", book.getIsbn());
    }

    @Test
    void shouldCreateABook() {
        Book bookOne = new Book(1004L, "Book4", "Author4", "ISBN4");
        ResponseEntity<Book> response = restTemplate.postForEntity("http://localhost:" + randomServerPort + "/books", bookOne, Book.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Book4", Objects.requireNonNull(response.getBody()).getTitle());
        assertEquals("Author4", response.getBody().getAuthor());
    }
}
