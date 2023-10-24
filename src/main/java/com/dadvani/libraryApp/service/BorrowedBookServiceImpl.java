package com.dadvani.libraryApp.service;


import com.dadvani.libraryApp.dto.BorrowedBookDto;
import com.dadvani.libraryApp.exceptions.BookWasPuttedException;
import com.dadvani.libraryApp.models.Book;
import com.dadvani.libraryApp.models.BorrowedBook;
import com.dadvani.libraryApp.repository.BorrowedBookRepository;
import com.dadvani.libraryApp.service.Iservice.BorrowedBookService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BorrowedBookServiceImpl implements BorrowedBookService {

    BorrowedBookRepository borrowedBookRepository;

    public BorrowedBookServiceImpl(BorrowedBookRepository borrowedBookRepository){
        this.borrowedBookRepository = borrowedBookRepository;
    }

    @Override
    public BorrowedBook findByIsbn(String isbn) {
        return borrowedBookRepository.findByIsbn(isbn);
    }


    @Override
    public BorrowedBook save(Book book, UserDetails userDetails, BorrowedBookDto borrowedBookDto) {
        BorrowedBook borrowedBook = new BorrowedBook(book.getIsbn(), userDetails.getUsername(),borrowedBookDto.getBorrowTime(),borrowedBookDto.getShouldBeReturned(),book.getTitle());
        return borrowedBookRepository.save(borrowedBook);
    }

    @Override
    public BorrowedBook get(Integer id) throws BookWasPuttedException {
        Optional<BorrowedBook> result = borrowedBookRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new BookWasPuttedException("book is already returned");
    }

    @Override
    public void deleteByIsbn(String isbn) {
        borrowedBookRepository.deleteByIsbn(isbn);
    }

    @Override
    public List<BorrowedBook> findByUsername(String username) {
        return borrowedBookRepository.findByUsername(username);
    }

    @Override
    public boolean bookIsAlreadyBorrowed(Book book,String username) {
        List<BorrowedBook> listbook = findByUsername(username);
        for (BorrowedBook br : listbook){
            if (Objects.equals(br.getIsbn(), book.getIsbn())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOverdue(LocalDateTime shouldBeReturned) {
        return LocalDateTime.now().isAfter(shouldBeReturned);
    }

    @Override
    public void deleteByUsernameAndIsbn(String username, String isbn) {
        borrowedBookRepository.deleteByUsernameAndIsbn(username, isbn);
    }

    @Override
    public void setOverdueLimit(BorrowedBookDto borrowedBookDto,LocalDateTime borrowTime,LocalDateTime returnTime) {
        borrowedBookDto.setBorrowTime(borrowTime);
        borrowedBookDto.setShouldBeReturned(returnTime);
    }
}
