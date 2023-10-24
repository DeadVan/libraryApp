package com.dadvani.libraryApp.controller.admin;

import com.dadvani.libraryApp.dto.BookDto;
import com.dadvani.libraryApp.exceptions.BookWasNotFoundException;
import com.dadvani.libraryApp.models.Book;
import com.dadvani.libraryApp.service.Iservice.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/addBook")
    public String addNewBook(Model model, BookDto bookDto) {
        model.addAttribute("book", bookDto);
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addedBookSave(@ModelAttribute("book") BookDto bookDto, Model model) {
        Book book = bookService.findByIsbn(bookDto.getIsbn());
        if (book != null) {
            model.addAttribute("isbnexists", book);
            return "addBook";
        }
        bookService.save(bookDto);
        return "redirect:/addBook?success";
    }

    @GetMapping("/admin-home/editBook/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Book book = bookService.get(id);
            model.addAttribute("book", book);
            model.addAttribute("pageTitle", "Edit Book (ID: " + id + ")");
            return "editBook";
        } catch (BookWasNotFoundException e) {
            ra.addFlashAttribute("alreadyDeleted", "sorry this book was deleted from library");
            return "redirect:/admin-home";
        }
    }

    @PostMapping("/admin-home/editBook/{id}")
    public String saveEditedBook(@PathVariable("id") Integer id, BookDto bookDto,RedirectAttributes ra){
        try {
            Book book = bookService.get(id);
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setGenre(bookDto.getGenre());
            book.setIsbn(bookDto.getIsbn());
            bookService.saveB(book);
            return "redirect:/admin-home/editBook/{id}?success";
        } catch (BookWasNotFoundException e) {
            ra.addFlashAttribute("alreadyDeleted", "sorry this book was deleted from library");
            return "redirect:/admin-home";
        }

    }

    @GetMapping("/admin-home/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            bookService.get(id);
            bookService.delete(id);
            ra.addFlashAttribute("message", "Book has been deleted.");
        }catch (BookWasNotFoundException e) {
            ra.addFlashAttribute("alreadyDeleted", "sorry this book was deleted from library");
        }
        return "redirect:/admin-home";
    }
}
