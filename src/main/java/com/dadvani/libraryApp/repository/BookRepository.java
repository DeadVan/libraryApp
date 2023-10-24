package com.dadvani.libraryApp.repository;

import com.dadvani.libraryApp.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findByIsbn(String isbn);
    Book findByTitle(String title);
    Long countById(Integer id);
}
