# Task 2: Database Integration & Migrations

## Objective
Connect Spring Boot application to MySQL and use Flyway for migrations.

## Technologies Used
- Java 17
- Spring Boot 3.5.14
- MySQL 8.0
- Spring Data JPA
- Flyway
- Lombok
- Maven

## Database Configuration
- Database: MySQL
- Database Name: employeedb
- Port: 3306

## Flyway Migrations

| Version | File | Description |
|---------|------|-------------|
| V1 | V1__create_employees_table.sql | Creates employees table |
| V2 | V2__seed_employees_data.sql | Seeds 5 sample employees |

## Migration Files Location
src/main/resources/db/migration/
├── V1__create_employees_table.sql
└── V2__seed_employees_data.sql

## Sample Data Seeded
| Name | Department | Salary |
|------|-----------|--------|
| Arjun Sharma | Engineering | 85000 |
| Priya Patel | Marketing | 65000 |
| Rahul Verma | HR | 60000 |
| Sneha Reddy | Finance | 72000 |
| Karthik Nair | Engineering | 90000 |

## How to Run
1. Create MySQL database:
```sql
CREATE DATABASE employeedb;
```
2. Update `application.properties` with your MySQL password
3. Run the app:
```bash
mvn spring-boot:run
```
Flyway will automatically apply migrations on startup.
