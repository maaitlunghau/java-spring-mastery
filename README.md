# java-spring-mastery

> A structured study repository for building production-grade web backends with the Spring ecosystem — from core fundamentals to two complete full-stack projects covering both Stateful and Stateless architectural models.

![Status](https://img.shields.io/badge/status-in%20progress-blue?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-6DB33F?style=flat-square&logo=springsecurity&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![JWT](https://img.shields.io/badge/Auth-JWT%20%2F%20OAuth2-000000?style=flat-square&logo=jsonwebtokens&logoColor=white)

---

## Overview

This repository documents a goal-oriented study path through the Spring ecosystem, structured around two practical projects that reflect real-world backend architecture patterns. The scope covers Spring Boot, Spring Data JPA, and Spring Security — from zero to a deployable backend application tested with Postman and documented with Swagger.

**Prerequisite:** [java-core-mastery](https://github.com/MSTSoftware/java-core-mastery) — OOP, Exception Handling, Lambda, Stream API, Collections, Annotations.

---

## Learning Objectives

By the end of this repository, the goal is to be able to:

- Configure and run a Spring Boot application from scratch without relying on scaffolding tools
- Design a relational database schema and implement the full data access layer with Spring Data JPA
- Implement authentication and authorization under both **Session-based** (Stateful) and **JWT/OAuth2** (Stateless) models
- Build and document a RESTful API, test it with Postman, and expose it via Swagger UI
- Understand the architectural tradeoffs between SSR/MVC and CSR/REST API patterns

---

## Roadmap

### Phase 1 — Spring Core & Boot Fundamentals `required`

| Topic | Notes |
|---|---|
| IoC & Dependency Injection | `@Component`, `@Service`, `@Repository`, `@Bean` |
| ApplicationContext & Bean lifecycle | Initialization, destruction, `@PostConstruct`, `@PreDestroy` |
| Bean scopes | Singleton vs Prototype, request/session scopes |
| Auto-configuration | How Spring Boot resolves and applies starters |
| `application.properties` / `.yml` | Externalized configuration, type-safe `@ConfigurationProperties` |
| Profiles | `@Profile`, `spring.profiles.active`, per-environment config |
| Dependency management | Starters, BOM, Maven/Gradle basics |

> Checkpoint: able to bootstrap a Spring Boot app, define beans explicitly and via component scanning, and manage environment-specific configuration.

---

### Phase 2 — Web Layer

#### 2a — MVC (Stateful / SSR)

| Topic | Notes |
|---|---|
| `@Controller` & Thymeleaf | Server-side rendering, template variables, layout dialect |
| Form handling | `@ModelAttribute`, binding, `BindingResult` |
| Bean Validation | `@Valid`, `@NotBlank`, `@Size`, custom `ConstraintValidator` |
| Static resources | CSS/JS serving, Webjars |
| Custom error pages | `ErrorController`, HTTP status mapping |

#### 2b — REST API (Stateless / CSR)

| Topic | Notes |
|---|---|
| `@RestController` & routing | `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` |
| Request/Response handling | `@RequestBody`, `@PathVariable`, `@RequestParam`, `ResponseEntity<T>` |
| DTO pattern | Separating domain model from API contract |
| Exception handling | `@ControllerAdvice`, `@ExceptionHandler`, `ProblemDetail` (RFC 7807) |
| OpenAPI / Swagger UI | `springdoc-openapi`, endpoint documentation, try-it-out |
| CORS configuration | `@CrossOrigin`, global `CorsConfigurationSource` |

> Checkpoint: able to build and document a complete REST API; handle validation errors and domain exceptions with a consistent response structure.

---

### Phase 3 — Spring Data JPA

| Topic | Notes |
|---|---|
| Entity design | `@Entity`, `@Id`, `@GeneratedValue`, `@Column`, `@Table` |
| Repository interfaces | `JpaRepository`, `CrudRepository`, `PagingAndSortingRepository` |
| Derived query methods | `findByEmailAndIsActive`, `existsByUsername` |
| JPQL & `@Query` | Custom queries, named parameters, `nativeQuery` |
| Entity relationships | `@OneToMany`, `@ManyToOne`, `@ManyToMany`, `fetch` strategies |
| Transactions | `@Transactional`, propagation levels, isolation levels |
| Pagination & sorting | `Pageable`, `Page<T>`, `Sort` |
| Database migration | Flyway — versioned migration scripts, baseline strategy |
| Connection pooling | HikariCP configuration, pool sizing |

> ⚠️ Fetch strategy (`LAZY` vs `EAGER`) and the N+1 query problem are the most common performance issues in JPA — understand both before writing repository queries.

> Checkpoint: able to design a normalized schema, implement the full repository layer, write custom JPQL queries, and manage transactions across service methods.

---

### Phase 4 — Spring Security

#### 4a — Stateful Authentication (Session-based)

| Topic | Notes |
|---|---|
| Security filter chain | `SecurityFilterChain`, `HttpSecurity` configuration |
| Form login | `formLogin()`, custom login page, success/failure handlers |
| `UserDetailsService` | Loading user from database, `UserDetails` contract |
| Password encoding | `BCryptPasswordEncoder`, upgrade strategies |
| Remember Me | `rememberMe()`, persistent token strategy |
| Session management | Concurrency control, session fixation protection |
| Role-based authorization | `hasRole()`, `hasAuthority()`, URL-level vs method-level |

#### 4b — Stateless Authentication (JWT + OAuth2)

| Topic | Notes |
|---|---|
| JWT fundamentals | Structure (header / payload / signature), claims, expiration |
| Access token & refresh token | Issuance, rotation, storage strategy |
| `OncePerRequestFilter` | JWT extraction and validation filter |
| OAuth2 Resource Server | Spring Security OAuth2 integration, JWT decoder |
| Method-level security | `@PreAuthorize`, `@PostAuthorize`, SpEL expressions |
| Token revocation | Refresh token blacklist strategy |

> ⚠️ The Stateful and Stateless security configurations require separate `SecurityFilterChain` beans — mixing session and stateless config is a common setup error.

> Checkpoint: able to implement both authentication models end-to-end; protect endpoints by role; issue, validate, and rotate JWT tokens.

---

### Phase 5 — Production Readiness `as needed`

| Topic | Notes |
|---|---|
| Spring Boot Actuator | Health checks, metrics, custom endpoints |
| Structured logging | SLF4J + Logback, log levels per package |
| Caching | `@Cacheable`, `@CacheEvict`, Redis integration |
| Async processing | `@Async`, `CompletableFuture`, thread pool configuration |
| Scheduled tasks | `@Scheduled`, cron expressions |
| Docker | `Dockerfile`, `docker-compose` with app + database |

---

## Projects

### Project 1 — Stateful Web Application (SSR / MVC)

**Architecture:** Spring MVC · Thymeleaf · Spring Data JPA · Spring Security (Session)

**Features to implement:**
- User registration and login with form-based authentication
- Remember Me with persistent token
- Role-based access control (e.g., ADMIN / USER)
- Full CRUD on at least one domain entity
- Server-rendered views with Thymeleaf layouts and partials

**Tech stack:**

| Layer | Technology |
|---|---|
| Web | Spring MVC, Thymeleaf |
| Data | Spring Data JPA, Flyway, MySQL / PostgreSQL |
| Auth | Spring Security, BCrypt, Remember Me |
| Build | Maven |

---

### Project 2 — Stateless RESTful API (CSR / REST)

**Architecture:** Spring Boot REST · Spring Data JPA · Spring Security (JWT + OAuth2)

**Features to implement:**
- Self-designed database schema and entity model
- Full RESTful API surface (CRUD + pagination + filtering)
- Authentication with access token / refresh token pair
- Protected endpoints with role and permission checks
- API documentation via Swagger UI
- Postman collection for all endpoints

**Tech stack:**

| Layer | Technology |
|---|---|
| Web | Spring REST, `ResponseEntity<T>`, DTO |
| Data | Spring Data JPA, Flyway, MySQL / PostgreSQL |
| Auth | Spring Security, JWT, OAuth2 Resource Server |
| Docs | Springdoc OpenAPI (Swagger UI) |
| Testing | Postman |
| Build | Maven |

---

## Repository Structure

```
java-spring-mastery/
├── phase-1-core/
│   ├── IoCDemo/
│   ├── BeanLifecycle/
│   └── ConfigurationDemo/
├── phase-2-web/
│   ├── mvc-thymeleaf/
│   └── rest-api/
├── phase-3-data-jpa/
│   ├── EntityRelationships/
│   ├── QueryMethods/
│   └── TransactionDemo/
├── phase-4-security/
│   ├── session-based/
│   └── jwt-oauth2/
├── projects/
│   ├── project-01-stateful/       # MVC + Thymeleaf + Session
│   └── project-02-stateless/      # REST API + JWT + OAuth2
└── README.md
```

Each phase contains isolated, runnable Spring Boot modules focused on a single concept. Projects are standalone applications with their own `pom.xml`, database migration scripts, and Postman collections.

---

## Tools & Environment

| Tool | Purpose |
|---|---|
| Java 21 (LTS) | Runtime |
| Spring Boot 3.x | Application framework |
| Maven | Build & dependency management |
| MySQL / PostgreSQL | Relational database |
| Flyway | Database migration |
| Postman | API testing |
| Swagger UI (Springdoc) | API documentation |
| Docker & Docker Compose | Local environment |
| IntelliJ IDEA / VS Code | IDE |

---

## Progress

| Phase | Status | Completion |
|---|---|---|
| Phase 1 — Spring Core & Boot | `not started` | 0% |
| Phase 2 — Web Layer (MVC + REST) | `not started` | 0% |
| Phase 3 — Spring Data JPA | `not started` | 0% |
| Phase 4 — Spring Security | `not started` | 0% |
| Phase 5 — Production Readiness | `not started` | — |
| Project 1 — Stateful (SSR/MVC) | `not started` | 0% |
| Project 2 — Stateless (REST/JWT) | `not started` | 0% |

---

## References

- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [Springdoc OpenAPI](https://springdoc.org/)
- [Baeldung — Spring Tutorials](https://www.baeldung.com/spring-tutorial)

---

## Author

**Mai Trung Hậu** — Co-Founder at [MST Software](https://github.com/MSTSoftware) · Full-stack Web Developer  
*Specializing in TypeScript / React / Next.js / .NET · Expanding into Java / Spring Boot backend*

---

*This repository is a personal engineering study log. Each module is written to be read, not just run — inline comments explain decisions, not just mechanics.*