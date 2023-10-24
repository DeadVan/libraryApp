package com.dadvani.libraryApp.service.Iservice;

import com.dadvani.libraryApp.dto.BorrowedBookDto;
import com.dadvani.libraryApp.exceptions.BookWasPuttedException;
import com.dadvani.libraryApp.models.Book;
import com.dadvani.libraryApp.models.BorrowedBook;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;


public interface BorrowedBookService {

    BorrowedBook findByIsbn(String isbn);
    BorrowedBook save (Book book, UserDetails user, BorrowedBookDto borrowedBookDto);
    BorrowedBook get(Integer id) throws BookWasPuttedException;
    void deleteByIsbn(String isbn);
    List<BorrowedBook> findByUsername(String username);
    boolean bookIsAlreadyBorrowed(Book book,String username);
    boolean isOverdue(LocalDateTime shouldBeReturned);

    void deleteByUsernameAndIsbn(String username, String isbn);
    void setOverdueLimit(BorrowedBookDto borrowedBookDto,LocalDateTime borrowTime,LocalDateTime returnTime);


}
