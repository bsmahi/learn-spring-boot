package com.springcourse.learnspringboot.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// It is one of the specializations of component annotation used for storing purpose
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
