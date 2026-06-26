# 🚀 AI-Powered CRM Platform

An enterprise-level **Customer Relationship Management (CRM)** system built using **Spring Boot**, **MySQL**, and **JWT Authentication**. The platform helps organizations manage customers, sales, support tickets, marketing campaigns, and notifications while providing secure role-based access control.

> **Status:** 🚧 Under Active Development

---

# 📌 Project Overview

This CRM platform is designed to simplify customer management by integrating sales, support, marketing, and administration into a single secure application.

The project follows a layered architecture and industry-standard backend practices including:

* RESTful APIs
* JWT Authentication
* Role-Based Authorization (RBAC)
* Global Exception Handling
* Validation
* Swagger API Documentation

Future versions will include AI/ML-powered analytics, Google OAuth, and a React frontend.

---

# ✨ Features

## 👤 User Management

* User Registration
* User Login
* JWT Authentication
* BCrypt Password Encryption
* Role-Based Authorization

## 👥 Customer Management

* Create Customer
* Update Customer
* Delete Customer
* View Customers

## 🎫 Ticket Management

* Create Support Tickets
* Ticket Comments
* Ticket Status Tracking

## 📦 Product Management

* Product CRUD Operations

## 🛒 Order Management

* Order CRUD Operations

## 📢 Campaign Management

* Marketing Campaigns
* Email Campaigns
* Notifications

## 🔐 Security

* JWT Authentication
* Role-Based Authorization (ADMIN, SALES, SUPPORT, MARKETING, MANAGER)
* Stateless Authentication
* Password Encryption using BCrypt

## 📖 API Documentation

* Swagger UI Integration

---

# 🛠 Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Maven

### Database

* MySQL

### Documentation

* Swagger (OpenAPI)

### Authentication

* JWT
* BCrypt Password Encoder

### Version Control

* Git
* GitHub

### Future Technologies

* React.js
* Google OAuth
* Machine Learning (Python)
* Scikit-learn
* Docker

---

# 📂 Project Structure

```text
src
│
├── Controller
├── Service
├── Repository
├── Models
│     ├── Entities
│     └── Enum
├── Security
├── Exception
├── DTO (Upcoming)
└── Configuration
```

---

# 🏗 Architecture

```text
React Frontend (Upcoming)
            │
            ▼
      Spring Boot REST API
            │
     JWT Authentication
            │
 Role-Based Authorization
            │
      Service Layer
            │
    Repository Layer
            │
          MySQL
```

---

# 🔒 Roles

| Role      | Access                           |
| --------- | -------------------------------- |
| ADMIN     | Full Access                      |
| SALES     | Customers, Products, Orders      |
| SUPPORT   | Tickets, Comments                |
| MARKETING | Campaigns, Emails, Notifications |
| MANAGER   | Dashboard & Reports (Upcoming)   |

---

# 📚 REST APIs

## Authentication

* POST `/api/auth/register`
* POST `/api/auth/login`

## Users

* GET `/api/users`
* GET `/api/users/{id}`
* POST `/api/users`
* PUT `/api/users/{id}`
* DELETE `/api/users/{id}`

## Customers

* CRUD APIs

## Products

* CRUD APIs

## Orders

* CRUD APIs

## Tickets

* CRUD APIs

## Comments

* CRUD APIs

## Campaigns

* CRUD APIs

## Emails

* CRUD APIs

## Notifications

* CRUD APIs

---

# 🧪 Testing

API testing is performed using **Postman**.

---

# 🔐 Security Features

* JWT Token Authentication
* BCrypt Password Encryption
* Stateless Session Management
* Role-Based Access Control (RBAC)
* Request Validation
* Global Exception Handling

---

# 🤖 Upcoming AI Features

* Customer Segmentation
* Customer Churn Prediction
* Ticket Priority Prediction
* Sales Forecasting
* AI-Powered Customer Insights
* Email Recommendation System

---

# 🚀 Future Enhancements

* DTO Layer
* Google OAuth Login
* React Frontend
* Dashboard Analytics
* Email Service
* Machine Learning Integration
* Docker Deployment
* Cloud Deployment

---

# ▶️ How to Run

## Clone Repository

```bash
git clone https://github.com/sudhakarvenkateshhh/ai-crm-platform.git
```

## Navigate

```bash
cd ai-crm-platform
```

## Configure

Update your `application.properties` with your MySQL configuration.

## Run

```bash
./mvnw spring-boot:run
```

---

# 📈 Project Progress

* ✅ Phase 1 – Core CRM
* ✅ Phase 2 – Marketing Module
* ✅ Phase 3 – Sales Module
* ✅ Phase 4 – Validation & Exception Handling
* ✅ Phase 5 – JWT Authentication
* ✅ Phase 6 – Role-Based Authorization
* 🔄 Phase 7 – DTO Layer
* ⏳ Google OAuth
* ⏳ Dashboard Analytics
* ⏳ Email Service
* ⏳ Machine Learning Integration
* ⏳ React Frontend
* ⏳ Deployment

---

# 👨‍💻 Author

**Sudhakar Venkatesh**

B.Tech Computer Science Engineering

VIT-AP University

---

⭐ If you found this project interesting, consider starring the repository.
