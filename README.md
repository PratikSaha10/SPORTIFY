# 🏆 Sportify - Online Sports Accessories Store

Sportify is a web-based e-commerce platform dedicated to providing a seamless shopping experience for sports enthusiasts.  
It allows users to browse, select, and purchase sports accessories such as football kits, cricket gear, gym equipment, and more.

This project is built using **Spring Boot**, **Thymeleaf**, and **MySQL**, following the MVC architecture with secure authentication using **Spring Security**.

---

## 🚀 Features

### 👤 User Features
- User Registration & Login (Spring Security)
- Browse sports products
- Add products to cart
- Remove products from cart
- View total cart price
- Edit user profile
- Secure logout

### 🛠️ Admin Features
- Admin login with role-based access
- Add new products
- Edit existing products
- Delete products
- View all products in Admin Dashboard

---

## 🧑‍💻 Technologies Used

### Frontend
- HTML5
- CSS3
- Bootstrap
- Thymeleaf (Template Engine)

### Backend
- Java
- Spring Boot
- Spring MVC
- Spring Security

### Database
- MySQL
- Spring Data JPA (Hibernate ORM)

### Other Tools
- BCrypt Password Encoder
- Maven
- Render (Deployment)

---

## 🏗️ System Architecture (MVC)

- **Model:** User, Product, Cart, CartItem
- **View:** Thymeleaf HTML templates
- **Controller:** Handles HTTP requests
- **Service Layer:** Business logic
- **Repository Layer:** Database operations (JPA)

Flow:  
`User → Controller → Service → Repository → Database → View`

---

## 🗄️ Database Design

### Entities:
- User
- Product
- Cart
- CartItem

### Relationships:
- User (1:1) Cart  
- Cart (1:N) CartItem  
- Product (1:N) CartItem  

---

## 🔐 Authentication & Security
- Implemented using Spring Security
- Role-based authorization (USER / ADMIN)
- Passwords encrypted using BCrypt
- Secure login and logout

---

## ⚙️ Installation & Setup

### Prerequisites
- Java 17+
- MySQL
- Maven
- IDE (STS / IntelliJ / Eclipse)

---

### Steps to Run Locally

 Clone the repository  
git clone https://github.com/PratikSaha10/SPORTIFY.git
