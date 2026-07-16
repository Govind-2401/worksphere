# 🚀 WorkSphere HRMS

<p align="center">
  <img src="screenshots/banner.png" alt="WorkSphere HRMS Banner" width="100%">
</p>

A production-ready Employee Management System built using **Spring Boot**, **Spring Security (JWT)**, **MySQL**, **Docker**, and **Railway**.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-green)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED)
![Railway](https://img.shields.io/badge/Deployment-Railway-purple)
![License](https://img.shields.io/badge/License-Apache%202.0-blue)

## 📌 Overview

WorkSphere HRMS is a RESTful backend application that helps organizations manage employees and departments securely. It provides JWT-based authentication, role-based authorization, CRUD operations, search, pagination, sorting, file upload support, Docker containerization, and cloud deployment.

## ✨ Features

- 🔐 JWT Authentication & Authorization
- 👥 Employee Management
- 🏢 Department Management
- 🔍 Search API
- 📄 Pagination & Sorting
- ✅ Bean Validation
- ⚠️ Global Exception Handling
- 📂 File Upload Support
- 🐳 Docker Support
- ☁️ Railway Deployment
- 📖 Swagger/OpenAPI Documentation

## 🛠️ Tech Stack

| Category | Technology |
|----------|------------|
| Language | Java 21 |
| Framework | Spring Boot 3.5 |
| Security | Spring Security + JWT |
| Database | MySQL 8 |
| ORM | Spring Data JPA (Hibernate) |
| Build Tool | Maven |
| API Testing | Postman |
| API Documentation | Swagger / OpenAPI |
| Containerization | Docker |
| Cloud Deployment | Railway |
| Version Control | Git & GitHub |

## 📂 Project Structure

```text
worksphere
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   └── test
├── uploads
├── screenshots
├── Dockerfile
├── pom.xml
└── README.md
```

## 🏗️ Architecture

```text
                Client (Postman / Swagger UI)
                           │
                           ▼
                    Spring Boot REST API
                           │
        ┌──────────────────┼──────────────────┐
        ▼                  ▼                  ▼
 Authentication      Employee Module    Department Module
   (JWT)                  │                  │
        └──────────────────┼──────────────────┘
                           ▼
                  Spring Data JPA (Hibernate)
                           │
                           ▼
                        MySQL Database
                           │
                           ▼
                  Docker & Railway Deployment
```

# 📷 Project Screenshots

## 🌐 Swagger UI

![Swagger UI](screenshots/api/swagger-home.png)

---

## 🔐 Authentication APIs

![Authentication APIs](screenshots/api/authentication-apis.png)

### User Registration

![Register](screenshots/api/register-success.png)

### User Login (JWT)

![Login](screenshots/api/login-jwt.png)

---

## 👥 Employee Management

![Employee APIs](screenshots/api/employee-apis.png)

### Create Employee

![Create Employee](screenshots/api/create-employee.png)

### Get Employees

![Get Employees](screenshots/api/get-employees.png)

---

## 🏢 Department Management

![Department APIs](screenshots/api/department-apis.png)

### Create Department

![Create Department](screenshots/api/create-department.png)

---

## 📊 Dashboard APIs

![Dashboard APIs](screenshots/api/dashboard-apis.png)

### Dashboard Summary

![Dashboard Summary](screenshots/api/dashboard-summary.png)

---

## ☁️ Railway Deployment

### Railway Dashboard

![Railway Dashboard](screenshots/deployment/railway-dashboard.png)

### Successful Deployment

![Railway Deployment](screenshots/deployment/railway-deployment.png)

### Railway Variables

![Railway Variables](screenshots/deployment/railway-variables.png)

### Live Swagger

![Swagger Live](screenshots/deployment/swagger-live.png)

---

## 🐳 Docker

### Docker Desktop

![Docker Desktop](screenshots/deployment/docker-containers.png)

### Running Containers

![Docker PS](screenshots/deployment/docker-ps.png)

---

## 🗄 Database

### Database Tables

![Database Tables](screenshots/database/mysql-tables.png)

### Users Table

![Users](screenshots/database/users-table.png)

### Employees Table

![Employees](screenshots/database/employees-table.png)

### Departments Table

![Departments](screenshots/database/departments-table.png)

---

## 📁 Project Structure

### Project Structure

![Project Structure](screenshots/project/project-structure.png)

### Package Structure

![Package Structure](screenshots/project/package-structure.png)

## 🌐 Live Deployment

| Service         | Link |
|-----------------|------|
| 🌐 API Base URL | https://worksphere-production-59a1.up.railway.app |
| 📘 Swagger UI   | https://worksphere-production-59a1.up.railway.app/swagger-ui/index.html |
| 📄 OpenAPI Docs | https://worksphere-production-59a1.up.railway.app/v3/api-docs |

## 📡 REST API Endpoints

### 🔐 Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and generate JWT token |

---

### 👥 Employee Management

| Method | Endpoint | Description          |
|--------|----------|----------------------|
| POST | `/api/employees` | Create employee      |
| GET | `/api/employees` | Get all employees    |
| GET | `/api/employees/{id}` | Get employee by ID   |
| PUT | `/api/employees/{id}` | Update employee      |
| DELETE | `/api/employees/{id}` | Delete employee      |
| GET | `/api/employees/search` | Search employees     |
| POST | `/api/employees/{id}/upload-profile-image` | Upload profile image |
| GET | `/api/employees/{id}/profile-image` | View profile image   |
| POST | `/api/employees/{id}/upload-resume` | Upload resume        |
| GET | `/api/employees/{id}/download-resume` | Download resume      |
| GET | `/api/employees?page=0&size=5&sortBy=firstName&sortDir=asc` | Pagination & Sorting |

---

### 🏢 Department Management

| Method | Endpoint                  | Description          |
|--------|---------------------------|----------------------|
| POST | `/api/departments`        | Create department    |
| GET | `/api/departments`        | Get all departments  |
| GET | `/api/departments/{id}`   | Get department by ID |
| PUT | `/api/departments/{id}`   | Update department    |
| GET | `/api/departments/search` | Search departments   |
| DELETE | `/api/departments/{id}`   | Delete department    |

---

### 📊 Dashboard

| Method | Endpoint                                 | Description                 |
|--------|------------------------------------------|-----------------------------|
| GET    | `/api/dashboard/summary`                 | Dashboard summary           |
| GET    | `/api/dashboard/employees-by-department` | Employee count by department |
| GET    | `/api/dashboard/gender-distribution`     | Gender statistics           |
| GET    | `/api/dashboard/recent-employees`        | Recently joined employees   |
| GET    | `/api/dashboard/employment-status`       | Employment status statistics |

## 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/Govind-2401/worksphere.git
cd worksphere
```

### Configure Database

Update the following properties in `application.properties`:

```properties
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Run the Project

```bash
mvn clean install
mvn spring-boot:run
```

### Run using Docker

```bash
docker compose up --build
```

## 🚀 Future Enhancements

- Email Notification
- Attendance Management
- Payroll Module
- Leave Management
- Performance Analytics
- Unit Testing
- CI/CD Pipeline

## 👨‍💻 Author

**Govind Kumar**

Backend Developer | Java | Spring Boot | MySQL | Docker

- GitHub: https://github.com/Govind-2401
- LinkedIn: https://www.linkedin.com/in/govind-kumar-486555368

## 📄 License

This project is licensed under the Apache 2.0 License.