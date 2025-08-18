# Movie reviews APIs

MovieAPI is a Spring Boot backend application for managing movies, users, authentication, and administration for a movie website.  
It supports user registration, login (with username or email), password reset, JWT authentication, MongoDB integration, and email notifications


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

## Environment Variables

Configuration is managed via a `.env` file located at `src/main/resources/.env`.  
**You must provide values for the following variables:**

```properties
# Application Configuration
APP_NAME=movies_backend
APP_PORT=8080
APP_BASE_URL=http://localhost:

# Database Connection
MONGO_USER=your_mongo_user
MONGO_PASSWORD=your_mongo_password
MONGO_CLUSTER=your_mongo_cluster_url
MONGO_DATABASE=your_database_name

# JWT Security
SECRET_KEY=your_jwt_secret_key
EXPIRATION_MS=86400000

# Email Configuration
EMAIL_HOST=smtp.gmail.com
EMAIL_PORT=587
EMAIL_USERNAME=your_email@gmail.com
EMAIL_PASSWORD=your_email_app_password

# Admin User Credentials
ADMIN_EMAIL=admin@movieapi.com
ADMIN_USERNAME=admin
ADMIN_PASSWORD=admin123
ADMIN_FIRST_NAME=System
ADMIN_LAST_NAME=Admin
```

```sh
./mvnw clean install
./mvnw spring-boot:run
```

### API Documentation

Swagger UI is available at: https://movies-reviews-api.onrender.com/swagger-ui/index.html

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
