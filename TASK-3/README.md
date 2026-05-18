# Task 3: Unit Testing & Logging

## Objective
Write unit tests for service classes and configure logging.

## Technologies Used
- Java 17
- Spring Boot 3.5.14
- JUnit 5
- Mockito
- SLF4J
- Logback
- Maven

## Test Coverage

| Test | Description |
|------|-------------|
| testGetAllEmployees | Verify fetching all employees |
| testGetEmployeeById_Success | Verify fetching by valid ID |
| testGetEmployeeById_NotFound | Verify error on invalid ID |
| testCreateEmployee_Success | Verify employee creation |
| testCreateEmployee_EmailAlreadyExists | Verify duplicate email error |
| testUpdateEmployee_Success | Verify employee update |
| testDeleteEmployee_Success | Verify employee deletion |
| testDeleteEmployee_NotFound | Verify error on invalid delete |

## Test Results
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS

## Logging Configuration
- **Console Logging** — All logs printed to terminal
- **File Logging** — Logs saved to `logs/employee-api.log`
- **Log Levels** — DEBUG for app, INFO for root

## How to Run Tests
```bash
mvn test
```

## How to View Logs
After running the app, check:
logs/employee-api.log
