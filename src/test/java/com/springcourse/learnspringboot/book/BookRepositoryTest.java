package com.springcourse.learnspringboot.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldReturnAllBooksList() {
        // When
        List<Book> books = this.bookRepository.findAll();

        // Then
        assertEquals(3, books.size());
    }

    @Test
    void shouldReturnBookById() {
        // When
        Optional<Book> books = this.bookRepository.findById(1001L);

        // Then
        assertTrue(books.isPresent());
    }

    @Test
    void shouldCreateABook() {
        // Given
        Book bookOne = new Book();
        bookOne.setId(1004L);
        bookOne.setTitle("AWS Lambda");
        bookOne.setAuthor("Mahi");
        bookOne.setIsbn("ISBN4");

        // When
        Book saveBook = this.bookRepository.save(bookOne);
        List<Book> books = this.bookRepository.findAll();

        // Then
        assertNotNull(saveBook);
        assertEquals(4, books.size());

    }

    @Test
    void shouldDeleteABookById() {
        // When
        this.bookRepository.deleteById(1001L);
        Optional<Book> books = this.bookRepository.findById(1001L);

        // Then
        assertFalse(books.isPresent());
    }
}
