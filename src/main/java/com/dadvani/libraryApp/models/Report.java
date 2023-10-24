package com.dadvani.libraryApp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reporting")
public class Report {

    public Report(String username, Boolean returnedOnTime, String isbn) {
        super();
        this.username = username;
        this.returnedOnTime = returnedOnTime;
        this.isbn = isbn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private Boolean returnedOnTime;
    private String isbn;
}
