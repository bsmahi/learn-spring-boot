package com.springcourse.learnspringboot.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @MockBean
    private BookRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnBooks() throws Exception {
        Book bookOne = new Book(1004L, "Book4", "Author4", "ISBN4");
        when(bookService.getAllBooks()).thenReturn(List.of(bookOne));
        when(repository.findAll()).thenReturn(List.of(bookOne));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("1004"))
                .andExpect(jsonPath("$[0].title").value("Book4"))
                .andExpect(jsonPath("$[0].author").value("Author4"));

    }

    @Test
    void shouldCreateABook() throws Exception {
        Book bookOne = new Book(1004L, "Book4", "Author4", "ISBN4");
        when(bookService.saveBook(any(Book.class))).thenReturn(bookOne);
        when(repository.save(any(Book.class))).thenReturn(bookOne);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Book4\", \"author\": \"Author4\", \"isbn\": \"ISBN4\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book4"));
    }

    @Test
    void shouldRemoveBookById() throws Exception {
        Book bookOne = new Book(1004L, "Book4", "Author4", "ISBN4");
        when(bookService.saveBook(any(Book.class))).thenReturn(bookOne);
        when(repository.save(any(Book.class))).thenReturn(bookOne);
        when(bookService.getBookById(1004L)).thenReturn(bookOne);
        when(repository.findById(1004L)).thenReturn(Optional.of(bookOne));

        mockMvc.perform(delete("/books/{id}", 1004L))
                .andExpect(status().isNoContent());

    }


}
