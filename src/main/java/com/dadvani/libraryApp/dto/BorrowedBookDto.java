package com.dadvani.libraryApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBookDto {

    private String isbn;
    private String username;
    private LocalDateTime borrowTime;
    private LocalDateTime shouldBeReturned;
    private String title;

}
