package com.dadvani.libraryApp.repository;

import com.dadvani.libraryApp.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Integer> {
}
