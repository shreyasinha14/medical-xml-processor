package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.model.Report;
import com.example.xmlprocessor.model.ReportXml;
import com.example.xmlprocessor.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Report> addReport(@RequestBody ReportXml xml) {
        Report report = new Report();
        report.setDeviceId(xml.getDeviceId());
        report.setTimestamp(xml.getTimestamp());
        report.setPatientId(xml.getPatientId());
        report.setReadingType(xml.getReadingType());
        report.setValue(xml.getValue());
        return new ResponseEntity<>(service.saveReport(report), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Report> getAll() {
        return service.getAllReports();
    }
}

@RestControllerAdvice
class XmlProcessingExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleXmlProcessingException(Exception ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Error processing XML: " + ex.getMessage());
    }
}