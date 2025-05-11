package com.example.xmlprocessor.service;

import com.example.xmlprocessor.model.Report;
import com.example.xmlprocessor.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository repository;

    public Report saveReport(Report report) {
        return repository.save(report);
    }

    public List<Report> getAllReports() {
        return repository.findAll();
    }
}
