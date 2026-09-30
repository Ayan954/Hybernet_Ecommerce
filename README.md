# Hibernate ORM E-Commerce Assignment

Simple Hibernate ORM project for an e-commerce system using Category, Product, Users, Orders and OrderDetails.

## Project Structure

```text
hibernate-ecommerce-assignment/
├── pom.xml
├── schema.sql
├── README.md
├── src/
│   ├── Category.java
│   ├── Product.java
│   ├── Users.java
│   ├── Orders.java
│   ├── OrderDetails.java
│   ├── Role.java
│   ├── HibernateUtil.java
│   ├── PasswordUtil.java
│   ├── Main.java
│   └── hibernate.cfg.xml
└── tests/
    └── HibernateTest.java
```

## Technologies

- Java 17
- Hibernate ORM
- JPA annotations
- H2 in-memory database
- Maven
- JUnit 5
- BCrypt for password hashing

## Relationships

- Category → Product: One-to-Many
- Product → Category: Many-to-One
- Users → Orders: One-to-Many
- Orders → Users: Many-to-One
- Orders → OrderDetails: One-to-Many
- OrderDetails → Orders: Many-to-One
- OrderDetails → Product: Many-to-One

## Run the Project

Open a terminal in the project folder and run:

```cmd
mvn clean compile
mvn exec:java
```

To run the test cases:

```cmd
mvn test
```

The application uses an H2 in-memory database. Hibernate creates and removes the database tables automatically during execution.

## CRUD / Test Coverage

The project demonstrates:

- Creating categories, products and users
- Creating an order with multiple order details
- Fetching an order with its user and products
- Updating product stock
- Deleting a product
- Password hashing and verification

`schema.sql` contains the corresponding database tables and foreign-key relationships.
