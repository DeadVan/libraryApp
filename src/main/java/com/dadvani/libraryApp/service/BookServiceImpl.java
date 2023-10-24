package com.dadvani.libraryApp.service;

import com.dadvani.libraryApp.dto.BookDto;
import com.dadvani.libraryApp.exceptions.BookWasNotFoundException;
import com.dadvani.libraryApp.models.Book;
import com.dadvani.libraryApp.repository.BookRepository;
import com.dadvani.libraryApp.service.Iservice.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book get(Integer id) throws BookWasNotFoundException {
        Optional<Book> result = bookRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new BookWasNotFoundException("can't find book id");
    }

    @Override
    public boolean bookIsAlreadyDeleted(String isbn) {
        return bookRepository.findByIsbn(isbn) == null;
    }

    @Override
    public void delete(Integer id) {
        Long count = bookRepository.countById(id);
        if (count == null || count == 0) {
        }
        bookRepository.deleteById(id);
    }
    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Book save(BookDto bookDto) {
        Book book = new Book(bookDto.getTitle(),bookDto.getAuthor(),bookDto.getIsbn(),bookDto.getGenre());
        return bookRepository.save(book);
    }

    @Override
    public Book saveB(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> returnBookList() {
        return bookRepository.findAll();
    }
    public List<Book> searchBooks(String searchQuery) {
        List<Book> allBooks = bookRepository.findAll(); // Assuming you have a method to get all books
        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : allBooks) {
            if (book.getTitle().contains(searchQuery) || book.getAuthor().contains(searchQuery)) {
                filteredBooks.add(book);
            }
        }

        return filteredBooks;
    }

}
