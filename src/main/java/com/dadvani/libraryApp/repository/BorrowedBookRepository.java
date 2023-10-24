package com.dadvani.libraryApp.repository;

import com.dadvani.libraryApp.models.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Integer> {
    BorrowedBook findByIsbn(String isbn);
    void deleteByIsbn(String isbn);
    List<BorrowedBook> findByUsername(String username);

    void deleteByUsernameAndIsbn(String username, String isbn);

}
