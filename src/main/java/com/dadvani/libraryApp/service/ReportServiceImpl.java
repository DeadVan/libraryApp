package com.dadvani.libraryApp.service;

import com.dadvani.libraryApp.models.BorrowedBook;
import com.dadvani.libraryApp.models.Report;
import com.dadvani.libraryApp.repository.ReportRepository;
import com.dadvani.libraryApp.service.Iservice.ReportService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }
    @Override
    public Report save(UserDetails userDetails, Boolean returnedOnTime, BorrowedBook borrowedBook) {
        Report report = new Report(userDetails.getUsername(),returnedOnTime,borrowedBook.getIsbn());
        return reportRepository.save(report);
    }

    @Override
    public List<Report> returnReports() {
        return reportRepository.findAll();
    }

    @Override
    public List<Report> returnNumberReports(int numberOfReports) {
        List<Report> allReports = reportRepository.findAll();
        int startIndex = Math.max(0, allReports.size() - numberOfReports);
        return allReports.subList(startIndex, allReports.size());
    }
}
