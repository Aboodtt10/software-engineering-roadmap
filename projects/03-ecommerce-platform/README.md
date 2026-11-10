# E-Commerce Platform — Project Blueprint

This directory documents the planned scope for the third portfolio project. It is intentionally marked as a blueprint rather than a completed shop.

## Planned stack

- Frontend: HTML, CSS, JavaScript
- Backend: Java 17 and Spring Boot
- Database: PostgreSQL
- Testing: JUnit and Spring Boot Test

## Planned milestones

1. Product catalog and product detail pages.
2. Shopping cart with quantity updates.
3. User registration and authentication.
4. Product, category, and order REST APIs.
5. PostgreSQL persistence and database migrations.
6. Order checkout flow using a simulated payment method (no real card processing).
7. Automated tests and deployment documentation.

## Planned domain model

- Product: id, name, description, price, stock quantity, category.
- User: id, name, email, password hash, role.
- CartItem: product, quantity.
- Order: id, user, items, total, status, created date.

## Security notes

Passwords must be hashed with a modern password-hashing algorithm. Secrets belong in environment variables, never in Git. Payment card details must not be collected or stored by this learning project.

## Status

**Planning / not implemented yet.** The implementation will be added in a later iteration after the API and database learning milestones.
