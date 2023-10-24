package com.dadvani.libraryApp.controller.user;


import com.dadvani.libraryApp.dto.BorrowedBookDto;
import com.dadvani.libraryApp.exceptions.BookWasNotFoundException;
import com.dadvani.libraryApp.exceptions.BookWasPuttedException;
import com.dadvani.libraryApp.models.Book;
import com.dadvani.libraryApp.models.BorrowedBook;
import com.dadvani.libraryApp.service.Iservice.BookService;
import com.dadvani.libraryApp.service.Iservice.BorrowedBookService;
import com.dadvani.libraryApp.service.Iservice.ReportService;
import com.dadvani.libraryApp.service.Iservice.UserService;
import com.dadvani.libraryApp.service.secService.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class BorrowController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BorrowedBookService borrowedBookService;
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    ReportService reportService;

    @GetMapping("/user-home/borrow/{id}")
    public String borrowBook(@PathVariable("id") Integer id, Principal principal, RedirectAttributes ra,
                             BorrowedBookDto borrowedBookDto) {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(principal.getName());
            Book book = bookService.get(id);
            LocalDateTime borrowTime = LocalDateTime.now();
            LocalDateTime returnTime = borrowTime.plusMinutes(1);
            borrowedBookService.setOverdueLimit(borrowedBookDto,borrowTime,returnTime);
            if (!userDetails.isMembershipStatus()) {
                ra.addFlashAttribute("message", "Expired membership status!\nPlease contact librarians");
            }else if (borrowedBookService.bookIsAlreadyBorrowed(book, userDetails.getUsername())) {
                ra.addFlashAttribute("message", "book is already borrowed");
            }else {
                ra.addFlashAttribute("borrow","you borrowed book");
                borrowedBookService.save(book,userDetails,borrowedBookDto);
            }
            return "redirect:/user-home?success\"";
        } catch (BookWasNotFoundException e) {
            ra.addFlashAttribute("alreadyReturned", "sorry book was deleted from library");
            return "redirect:/user-home";
        }
    }
    @GetMapping("/user-home/return/{id}")
    @Transactional
    public String returnBook(@PathVariable("id") Integer id,Principal principal, RedirectAttributes ra){
        try {
            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(principal.getName());
            BorrowedBook borrowedBook = borrowedBookService.get(id);
            if (borrowedBookService.isOverdue(borrowedBook.getShouldBeReturned())){
                ra.addFlashAttribute("alreadyReturned", "Book was Overdue, please return on time!");
            }
            borrowedBookService.deleteByUsernameAndIsbn(borrowedBook.getUsername(),borrowedBook.getIsbn());
            reportService.save(userDetails,!borrowedBookService.isOverdue(borrowedBook.getShouldBeReturned()),borrowedBook);
            return "redirect:/user-home?success\"";
        } catch (BookWasPuttedException e) {
            ra.addFlashAttribute("alreadyReturned", "Book was already returned into library");
            return "redirect:/user-home";
        }
    }
}
