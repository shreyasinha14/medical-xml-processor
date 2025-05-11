package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.model.Report;
import com.example.xmlprocessor.model.ReportXml;
import com.example.xmlprocessor.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ReportControllerTest {

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddReport() {
        // Create test data
        ReportXml reportXml = new ReportXml();
        // Set properties for reportXml
        
        Report savedReport = new Report();
        savedReport.setId(1L);
        // Set other properties

        // Mock service behavior
        when(reportService.saveReport(any(Report.class))).thenReturn(savedReport);

        // Call controller method
        ResponseEntity<Report> response = reportController.addReport(reportXml);

        // Verify the result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testGetAll() {
        // Create test data
        Report report1 = new Report();
        report1.setId(1L);
        
        Report report2 = new Report();
        report2.setId(2L);

        // Mock service behavior
        when(reportService.getAllReports()).thenReturn(Arrays.asList(report1, report2));

        // Call controller method
        List<Report> reports = reportController.getAll();

        // Verify the result
        assertEquals(2, reports.size());
        assertEquals(1L, reports.get(0).getId());
        assertEquals(2L, reports.get(1).getId());
    }
    
    @Test
    public void testExceptionHandler() {
        // Create test exception
        Exception exception = new Exception("Test error");
        
        // Create exception handler
        XmlProcessingExceptionHandler exceptionHandler = new XmlProcessingExceptionHandler();
        
        // Call exception handler method
        ResponseEntity<String> response = exceptionHandler.handleXmlProcessingException(exception);
        
        // Verify the result
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error processing XML: Test error", response.getBody());
    }
}