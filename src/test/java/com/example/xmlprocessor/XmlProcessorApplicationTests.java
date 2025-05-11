package com.example.xmlprocessor;

import com.example.xmlprocessor.model.Report;
import com.example.xmlprocessor.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XmlProcessorApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReportRepository reportRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testAddReport() {
        // Create XML payload
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<Report>" +
                "<deviceId>test-device</deviceId>" +
                "<patientId>test-patient</patientId>" +
                "<readingType>test-reading</readingType>" +
                "<value>test-value</value>" +
                "<timestamp>2023-05-12T10:30:00</timestamp>" +
                "</Report>";

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(xml, headers);

        // Send request
        ResponseEntity<Report> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/reports",
                request,
                Report.class);

        // Verify response
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("test-device", response.getBody().getDeviceId());
    }

    @Test
    public void testGetAllReports() {
        // Add a test report to the database
        Report report = new Report();
        report.setDeviceId("get-all-test");
        report.setPatientId("patient1");
        report.setReadingType("reading1");
        report.setValue("value1");
        report.setTimestamp(LocalDateTime.now());
        reportRepository.save(report);

        // Send request
        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/reports",
                List.class);

        // Verify response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}