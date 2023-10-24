package com.dadvani.libraryApp.service.Iservice;

import com.dadvani.libraryApp.models.BorrowedBook;
import com.dadvani.libraryApp.models.Report;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ReportService {
    Report save (UserDetails userDetails, Boolean returnedOnTime, BorrowedBook borrowedBook);
    List<Report> returnReports();
    List<Report> returnNumberReports(int numberOfReports);


}
