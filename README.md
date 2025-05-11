# Medical XML Processor

A Spring Boot application for processing medical device reports in XML format.

## Overview

This application provides a REST API for receiving and storing medical device reports in XML format. It uses Spring Boot, JPA, and H2 database to process and store the data.

## Features

- XML data processing using JAXB
- RESTful API endpoints
- In-memory H2 database for data storage
- Swagger UI for API documentation

## Technologies

- Java 8
- Spring Boot 2.7.18
- Spring Data JPA
- H2 Database
- JAXB for XML processing
- Swagger/SpringFox for API documentation

## API Endpoints

- `POST /api/reports` - Submit a new medical device report in XML format
- `GET /api/reports` - Retrieve all stored reports

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run `mvn spring-boot:run`
4. The application will start on http://localhost:8080

### API Documentation

Swagger UI is available at: http://localhost:8080/swagger-ui/

## XML Format

```xml
<Report>
  <deviceId>device123</deviceId>
  <patientId>patient456</patientId>
  <readingType>bloodPressure</readingType>
  <value>120/80</value>
  <timestamp>2023-05-12T10:30:00</timestamp>
</Report>
```