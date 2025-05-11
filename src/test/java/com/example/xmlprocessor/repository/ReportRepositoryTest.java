package com.example.xmlprocessor.repository;

import com.example.xmlprocessor.model.Report;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ReportRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReportRepository reportRepository;

    @Test
    public void testSaveReport() {
        // Create a test report
        Report report = new Report();
        report.setDeviceId("test-device");
        report.setPatientId("test-patient");
        report.setReadingType("test-reading");
        report.setValue("test-value");
        report.setTimestamp(LocalDateTime.now());

        // Save the report
        Report savedReport = reportRepository.save(report);

        // Verify the report was saved
        assertNotNull(savedReport.getId());
        assertEquals("test-device", savedReport.getDeviceId());
    }

    @Test
    public void testFindAllReports() {
        // Create and save multiple test reports
        Report report1 = new Report();
        report1.setDeviceId("device1");
        report1.setPatientId("patient1");
        report1.setReadingType("reading1");
        report1.setValue("value1");
        report1.setTimestamp(LocalDateTime.now());
        entityManager.persist(report1);

        Report report2 = new Report();
        report2.setDeviceId("device2");
        report2.setPatientId("patient2");
        report2.setReadingType("reading2");
        report2.setValue("value2");
        report2.setTimestamp(LocalDateTime.now());
        entityManager.persist(report2);

        // Find all reports
        List<Report> reports = reportRepository.findAll();

        // Verify the reports were found
        assertEquals(2, reports.size());
    }
}