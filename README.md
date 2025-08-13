# Movies Backend

A RESTful API for managing movies, users, and reviews. Built with Spring Boot, MongoDB, and JWT authentication.

## Features

- User registration, authentication (JWT), and profile management
- CRUD operations for movies and reviews
- Role-based access control (ADMIN, USER)
- Password reset via email
- API documentation with Swagger UI

## Technologies

- Java 17
- Spring Boot
- Spring Security
- MongoDB
- JWT (JSON Web Tokens)
- Lombok
- Swagger (OpenAPI)
- Maven

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MongoDB database

### Build & Run

```sh
./mvnw clean install
./mvnw spring-boot:run
```

### API Documentation

Swagger UI is available at:  
`http://localhost:8080/swagger-ui/index.html`

## Main Endpoints

- `POST /api/v1/auth/signup` — Register user
- `POST /api/v1/auth/signin` — Login
- `POST /api/v1/auth/logout` — Logout (JWT blacklist)
- `POST /api/v1/auth/forgot-password` — Request password reset
- `POST /api/v1/auth/reset-password` — Reset password

- `GET /api/v1/movies` — List movies
- `POST /api/v1/movies` — Create movie (ADMIN)
- `PUT /api/v1/movies/{id}` — Update movie (ADMIN)
- `DELETE /api/v1/movies/{id}` — Delete movie (ADMIN)

- `GET /api/v1/reviews` — List reviews
- `POST /api/v1/reviews` — Add review (USER/ADMIN)
- `PUT /api/v1/reviews/{id}` — Update review (USER/ADMIN)
- `DELETE /api/v1/reviews/{id}` — Delete review (ADMIN)

- `GET /api/v1/profile` — Get current user profile
- `PUT /api/v1/profile` — Update profile
- `PUT /api/v1/profile/update-password` — Change password

For questions, contact `Abdelrahman` (mailto:abdelrahman.kamel.elgendy@gmail.com).