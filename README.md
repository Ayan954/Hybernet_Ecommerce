# Hibernate ORM E-Commerce Assignment

This project implements the e-commerce system from the assignment using Java, Hibernate ORM, Maven and an H2 in-memory database.

## Entities

- Category
- Product
- Users
- Orders
- OrderDetails

## Relationships

- Category -> Product: One-to-Many
- Product -> Category: Many-to-One
- Users -> Orders: One-to-Many
- Orders -> Users: Many-to-One
- Orders -> OrderDetails: One-to-Many
- OrderDetails -> Orders: Many-to-One
- OrderDetails -> Product: Many-to-One

## Technologies

- Java 17
- Maven
- Hibernate ORM
- JPA annotations
- H2 database
- BCrypt
- JUnit 5

## Project structure

```text
src/
├── main/
│   ├── java/com/novamart/ecommerce/
│   │   ├── EcommerceApp.java
│   │   ├── model/
│   │   ├── dao/
│   │   ├── service/
│   │   ├── security/
│   │   └── util/
│   └── resources/
│       └── hibernate.cfg.xml
└── test/
    └── java/com/novamart/ecommerce/
        └── EcommerceIntegrationTest.java
```

## Database

The project uses H2 in-memory database, so no separate database installation is required.

Hibernate creates the tables automatically with:

```text
hibernate.hbm2ddl.auto=create-drop
```

A `schema.sql` file is also included for reference.

## Run the application

Open CMD in the project folder and run:

```cmd
mvn clean compile
mvn exec:java
```

The program demonstrates:

1. Creating categories.
2. Creating products.
3. Creating a customer.
4. Hashing and checking a password.
5. Creating an order with multiple order details.
6. Fetching an order with its user and products.
7. Fetching products by category.
8. Running a CriteriaBuilder query.
9. Pagination.

## Run tests

```cmd
mvn test
```

The tests cover basic CRUD, password hashing, orders with multiple details, associated data fetching, CriteriaBuilder, and pagination.

## Hibernate configuration

`hibernate.cfg.xml` contains the database connection, Hibernate settings and entity mappings.

## Assignment features

The required entity mappings and CRUD operations are implemented. The optional features included are:

- Named query
- CriteriaBuilder query
- Pagination

## Notes

The password is stored as a BCrypt hash rather than plain text.

The project is intended for the Hibernate ORM assignment and uses simple classes and methods so that the implementation is easy to understand and explain.
