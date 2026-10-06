# Spring Security practical task

Secure the supplied Spring Boot application.

The project already contains:

- A JPA-backed `Item` entity and CRUD REST API
- A Thymeleaf page at `/items`
- H2 sample data
- An intentionally incomplete `SecurityConfig`

The application currently permits all requests. Your task is to replace this
starter configuration with a working security solution.

## Running the application

On Windows:

```text
.\mvnw.cmd spring-boot:run
```

The application runs at:

```text
http://localhost:8080
```

The project uses Spring Boot 3.4.1 and Java 21.

## Your task

### Authentication

- Use BCrypt password encoding.
- Configure these development users:
  - `user` / `user` with role `USER`
  - `admin` / `admin` with role `ADMIN`
- Support browser form login.
- Support HTTP Basic for REST clients such as Postman.

### Browser UI

Protect `/items`.

- Unauthenticated users should be sent to login.
- Authenticated users should be able to view the page.
- The create and delete forms must work.
- Keep CSRF protection enabled for browser forms.
- Logout must end the session.

### REST API

Apply these rules:

| Method | Path | Access |
| --- | --- | --- |
| GET | `/api/items/**` | Any authenticated user |
| POST | `/api/items` | `ADMIN` only |
| PUT | `/api/items/**` | `ADMIN` only |
| DELETE | `/api/items/**` | `ADMIN` only |

The backend must enforce these rules. Do not rely on hiding UI buttons.

API requests without valid credentials should return `401 Unauthorized`.
Authenticated users without the required role should receive `403 Forbidden`.

### Outbound API call

Add one service-to-service HTTP call using the HTTP client covered in the
external APIs module.

The call must:

- Send an API key, Basic Auth credential, or bearer token
- Read the credential from configuration or an environment variable
- Never store a real secret in source code or Git
- Handle successful responses and external failures clearly

## Test your solution

Verify at least these cases:

- Browser request without a session → login page
- REST request without credentials → `401`
- `USER` can read items
- `USER` cannot create, update, or delete items → `403`
- `ADMIN` can create, update, and delete items
- Browser forms work with CSRF protection enabled
- Logout ends the browser session
- Outbound API success and failure are handled

## Existing endpoints

### REST API

```text
GET    /api/items
GET    /api/items/{id}
POST   /api/items
PUT    /api/items/{id}
DELETE /api/items/{id}
```

### Browser UI

```text
GET  /items
POST /items
POST /items/{id}/delete
```

Keep the implementation focused on the requirements above. JWT
authentication is not required for this task.
