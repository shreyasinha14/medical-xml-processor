package com.example.xmlprocessor.service;

import com.example.xmlprocessor.model.Report;
import com.example.xmlprocessor.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveReport() {
        // Create a test report
        Report report = new Report();
        report.setDeviceId("test-device");
        report.setPatientId("test-patient");
        report.setReadingType("test-reading");
        report.setValue("test-value");
        report.setTimestamp(LocalDateTime.now());

        // Mock repository behavior
        when(reportRepository.save(any(Report.class))).thenReturn(report);

        // Call service method
        Report savedReport = reportService.saveReport(report);

        // Verify the result
        assertNotNull(savedReport);
        assertEquals("test-device", savedReport.getDeviceId());
    }

    @Test
    public void testGetAllReports() {
        // Create test reports
        Report report1 = new Report();
        report1.setDeviceId("device1");
        
        Report report2 = new Report();
        report2.setDeviceId("device2");

        // Mock repository behavior
        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        // Call service method
        List<Report> reports = reportService.getAllReports();

        // Verify the result
        assertEquals(2, reports.size());
        assertEquals("device1", reports.get(0).getDeviceId());
        assertEquals("device2", reports.get(1).getDeviceId());
    }
}