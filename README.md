#  E-Commerce System

A full-stack **E-Commerce Web Application** built using **React.js, Spring Boot, and MySQL**.  
The platform allows users to browse products, add them to a cart or wishlist, place orders, and complete payments online.  
It also includes an **Admin Dashboard** for managing products, categories, and customer orders.

---

#  Live Demo

🌐 Frontend (Vercel)  
https://e-commerce-frontend-kohl-five.vercel.app

 GitHub Repository  
https://github.com/Sanika1319/E-Commerce-System

---

#  Features

###  User Features
- User Registration & Login
- Browse Products
- Add Products to Cart
- Wishlist Functionality
- Place Orders
- Secure Payment
- View Order History

###  Admin Features
- Admin Dashboard
- Add / Edit / Delete Products
- Manage Categories
- Manage Orders
- Monitor Store Activity

---

#  Tech Stack

### Frontend
- React.js
- React Router
- Axios
- SweetAlert2
- Bootstrap / CSS

### Backend
- Java
- Spring Boot
- Spring Security
- Hibernate / JPA
- REST APIs

### Database
- MySQL

### Deployment
- Vercel (Frontend)

---

#  Project Structure

## Backend Project Structure

```
src
└── main
    └── java
        └── com
            └── ecommerce
                ├── controller
                ├── service
                ├── repository
                ├── entity
                ├── config
                └── EcommerceApplication.java
```


# Installation & Setup

1️ Clone the Repository
- git clone https://github.com/Sanika1319/E-Commerce-System.git

2️ Navigate to Backend Directory
-cd backend

3️ Configure Database

Update application.properties

```
spring.datasource.url=jdbc:mysql://localhost:3306/E_Commerce_ITV
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4️ Run the Application
Using Maven

```
mvn spring-boot:run

```
Or Run the Main Class

ECommerceApplication.java

🌐 Base API URL

http://localhost:8080/

# API Endpoints

Authentication APIs

| Method | Endpoint       | Description       |
| ------ | -------------- | ----------------- |
| POST   | /auth/register | Register new user |
| POST   | /auth/login    | Login user        |


Product APIs

| Method | Endpoint       | Description                  |
| ------ | -------------- | ---------------------------- |
| GET    | /products      | Get all products             |
| GET    | /products/{id} | Get product by ID            |
| POST   | /products      | Add new product (Admin only) |
| PUT    | /products/{id} | Update product               |
| DELETE | /products/{id} | Delete product               |


Cart APIs

| Method | Endpoint          | Description         |
| ------ | ----------------- | ------------------- |
| POST   | /cart/add         | Add product to cart |
| GET    | /cart/{userId}    | Get user cart       |
| DELETE | /cart/remove/{id} | Remove cart item    |


Order APIs

| Method | Endpoint                | Description            | Access |
| ------ | ----------------------- | ---------------------- | ------ |
| POST   | /order/create/{userId}  | Create order           | User   |
| GET    | /order/{orderId}        | Get order details      | User   |
| GET    | /order/user/{userId}    | Get all orders of user | User   |
| GET    | /order/allOrders        | Get all orders         | Admin  |
| PUT    | /order/update/{orderId} | Update order status    | Admin  |
| DELETE | /order/delete/{orderId} | Delete order           | Admin  |


Category APIs

| Method | Endpoint         | Description          | Access |
| ------ | ---------------- | -------------------- | ------ |
| POST   | /category/create | Create category      | Admin  |
| GET    | /category/{id}   | Get category by ID   | Public |
| GET    | /category/       | Get all categories   | Public |
| GET    | /category/name   | Get category by name | Public |


Wishlist APIs

| Method | Endpoint                              | Description             | Access |
| ------ | ------------------------------------- | ----------------------- | ------ |
| POST   | /wishlist/add/{userId}/{productId}    | Add product to wishlist | User   |
| GET    | /wishlist/user/{userId}               | Get user wishlist       | User   |
| DELETE | /wishlist/remove/{userId}/{productId} | Remove from wishlist    | User   |


User APIs

| Method | Endpoint              | Description         | Access |
| ------ | --------------------- | ------------------- | ------ |
| GET    | /user/{userId}        | Get user by ID      | User   |
| GET    | /user/                | Get all users       | Admin  |
| GET    | /user/byEmail         | Get user by email   | Admin  |
| PUT    | /user/update/{userId} | Update user details | User   |
| DELETE | /user/delete/{userId} | Delete user         | Admin  |


#  Security

The application uses Spring Security for authentication and authorization.

Role Based Access

- ROLE_ADMIN
  
- ROLE_USER

# API Testing

You can test APIs using:

- Postman

- Thunder Client

- Swagger

#  Deployment

Frontend deployed on Vercel
```
https://e-commerce-frontend-kohl-five.vercel.app

```
# Author

Sanika Dattatray Mulik

Backend Developer
Java | Spring Boot | React | MySQL

GitHub
```
https://github.com/Sanika1319

```

# Support

If you found this project helpful:

⭐ Star the repository
🍴 Fork the project



