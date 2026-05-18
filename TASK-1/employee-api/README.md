# Task 1: RESTful API with Spring Boot

## Objective
Build a CRUD REST API for Employee resource using Spring Boot.

## Technologies Used
- Java 17
- Spring Boot 3.5.14
- Spring Web
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- Maven

## Project Structure
src/main/java/com/internspark/employee_api/
├── controller/EmployeeController.java
├── service/EmployeeService.java
├── repository/EmployeeRepository.java
├── model/Employee.java
├── dto/EmployeeDTO.java
└── EmployeeApiApplication.java

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/employees | Get all employees |
| GET | /api/employees/{id} | Get employee by ID |
| POST | /api/employees | Create new employee |
| PUT | /api/employees/{id} | Update employee |
| DELETE | /api/employees/{id} | Delete employee |

## Sample Request (POST)
```json
{
    "name": "Arjun Sharma",
    "email": "arjun.sharma@example.com",
    "department": "Engineering",
    "salary": 85000.00
}
```

## How to Run
```bash
mvn spring-boot:run
```
API will start at: http://localhost:8080
