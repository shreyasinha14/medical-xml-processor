package com.example.xmlprocessor.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name = "Report")
public class ReportXml {
    private Long id;
    private String deviceId;
    private String patientId;
    private String readingType;
    private String value;
    private LocalDateTime timestamp;
    public String getDeviceId() {
        return deviceId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getReadingType() {
        return readingType;
    }

    public String getValue() {
        return value;
    }
    // Fields and methods remain the same
}
