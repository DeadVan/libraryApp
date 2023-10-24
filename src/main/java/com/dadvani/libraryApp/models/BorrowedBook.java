package com.dadvani.libraryApp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "borrowedBooks")
public class BorrowedBook {

    public BorrowedBook(String isbn,String username,LocalDateTime borrowTime,LocalDateTime shouldBeReturned,String title) {
        super();
        this.isbn = isbn;
        this.username = username;
        this.borrowTime = borrowTime;
        this.shouldBeReturned = shouldBeReturned;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String isbn;
    private String username;
    private LocalDateTime borrowTime;
    private LocalDateTime shouldBeReturned;
    private String title;



}
