package com.dadvani.libraryApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportingDto {

    private int id;
    private String username;
    private Boolean returnedOnTime;
    private String isbn;
}
