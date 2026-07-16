# 🚀 WorkSphere HRMS

A production-ready Employee Management System built using **Spring Boot**, **Spring Security (JWT)**, **MySQL**, **Docker**, and **Railway**.

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

## 🌐 Live Deployment

| Service | Link |
|---------|------|
| 🚀 Live API | https://worksphere-production-59a1.up.railway.app |
| 📘 Swagger UI | https://worksphere-production-59a1.up.railway.app/swagger-ui/index.html |
| 📄 OpenAPI Docs | https://worksphere-production-59a1.up.railway.app/v3/api-docs |
