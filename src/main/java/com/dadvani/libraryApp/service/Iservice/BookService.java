package com.dadvani.libraryApp.service.Iservice;

import com.dadvani.libraryApp.dto.BookDto;
import com.dadvani.libraryApp.exceptions.BookWasNotFoundException;
import com.dadvani.libraryApp.models.Book;

import java.util.List;

public interface BookService {
    Book findByTitle(String title);
    Book findByIsbn(String isbn);
    Book save (BookDto bookDto);
    Book saveB (Book book);
    List<Book> returnBookList();
    Book get(Integer id) throws BookWasNotFoundException;
    boolean bookIsAlreadyDeleted(String isbn);
    List<Book> searchBooks(String searchQuery);
    void delete(Integer id);

}

