package com.dadvani.libraryApp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="books")
public class Book {

    public Book(String title, String author, String isbn, String genre) {
        super();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
}
