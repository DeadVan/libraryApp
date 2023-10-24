package com.dadvani.libraryApp.controller;

import com.dadvani.libraryApp.dto.UserDto;
import com.dadvani.libraryApp.models.Book;
import com.dadvani.libraryApp.models.User;
import com.dadvani.libraryApp.service.Iservice.BookService;
import com.dadvani.libraryApp.service.Iservice.BorrowedBookService;
import com.dadvani.libraryApp.service.Iservice.ReportService;
import com.dadvani.libraryApp.service.Iservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@Controller
public class MainController {

    @Autowired
    private UserDetailsService userDetailsService;

    private UserService userService;
    private BookService bookService;
    private BorrowedBookService borrowedBookService;
    private ReportService reportService;

    public MainController(UserService userService, BookService bookService,
                          BorrowedBookService borrowedBookService, ReportService reportService) {
        this.userService = userService;
        this.bookService = bookService;
        this.borrowedBookService = borrowedBookService;
        this.reportService = reportService;
    }

    @GetMapping("/admin-home")
    public String adminHome(Model model, Principal principal, @RequestParam(required = false) String searchQuery) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);

        if (searchQuery != null && !searchQuery.isEmpty()) {
            List<Book> filteredBooks = bookService.searchBooks(searchQuery);
            model.addAttribute("books", filteredBooks);
        } else {
            model.addAttribute("books", bookService.returnBookList());
        }
        model.addAttribute("users",userService.findByRole("USER"));
        model.addAttribute("reports", reportService.returnNumberReports(10));
        return "admin-home";
    }

    @GetMapping("/user-home")
    public String userHome(Model model,Principal principal,@RequestParam(required = false) String searchQuery) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);
        if (searchQuery != null && !searchQuery.isEmpty()) {
            List<Book> filteredBooks = bookService.searchBooks(searchQuery);
            model.addAttribute("books", filteredBooks);
        } else {
            model.addAttribute("books", bookService.returnBookList());
        }
        model.addAttribute("borrowedBooks",borrowedBookService.findByUsername(principal.getName()));
        return "user-home";
    }

    @GetMapping("/login")
    public String login(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") UserDto userDto, Model model) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null) {
            model.addAttribute("userexist", user);
            return "register";
        }
        userService.save(userDto);
        return "redirect:/register?success";
    }
}
