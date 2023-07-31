package com.springcourse.learnspringboot.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldReturnAllBooks() {
        // Given
        Book bookOne = new Book(1004L, "Book4", "Author4", "ISBN4");

        // When
        when(bookRepository.findAll()).thenReturn(List.of(bookOne));
        List<Book> books = bookService.getAllBooks();

        // Then
        assertThat(books).hasSize(1);
        verify(this.bookRepository).findAll();

    }

    @Test
    void shouldReturnBookById() {
        // Given
        Book bookOne = new Book(1004L, "Book4", "Author4", "ISBN4");

        // When
        when(bookRepository.findById(1004L)).thenReturn(Optional.of(bookOne));
        Optional<Book> returnedBook = Optional.ofNullable(this.bookService.getBookById(1004L));

        // Then
        assertEquals(bookOne.getId(), returnedBook.get().getId());
        verify(this.bookRepository).findById(1004L);

    }

    @Test
    void shouldCreateANewBook() {
        // Given
        Book bookOne = new Book(1004L, "Book4", "Author4", "ISBN4");

        // When
        this.bookService.saveBook(bookOne);

        // Then
        verify(this.bookRepository).save(bookOne);

    }

    @Test
    void shouldDeleteABook() {
        // When
        this.bookService.deleteBook(1004L);

        // Then
        verify(this.bookRepository).deleteById(1004L);

    }


}
