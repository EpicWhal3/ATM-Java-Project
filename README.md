# Banking System

A multi-module backend project in Java and Spring Boot that implements a banking system with a REST API, a separate API Gateway, JWT authentication, and asynchronous event processing via Kafka.

The project is built as a set of related services:

- **bank-presentation** — the main HTTP API of the banking system
- **bank-application** — business logic
- **bank-infrastructure** — entities, DTOs, repositories, and general infrastructure
- **API-Gateway** — an entry point with authentication and role access
- **Storage** — a Kafka event consumer service that saves the history of changes in the database

## What is implemented

The system supports:

- user registration and storage;
- creation of bank accounts;
- account replenishment;
- withdrawal of funds;
- transfers between accounts;
- viewing the balance;
- viewing the transaction history;
- working with the user's friends;
- filtering users by gender and hair color;
- the **ADMIN / CLIENT** role model;
- login using a login and password with obtaining a **JWT**;
- logout with adding the token to the blacklist;
- publishing events about user and account changes in **Kafka**;
- a separate asynchronous event storage.

## Architecture

### 1. The main banking service

The business logic is divided into three modules:

#### `bank-application`

Contains application services and mappers:

- `AccountService`
- `UserService`
- `KafkaEventProducer`
- `AccountMapper`
- `UserMapper`
- `TransactionMapper`

The main scenarios are:

- creating an account for a user;
- depositing and withdrawing funds;
- transferring funds between accounts;
- calculating the commission for transfers;
- retrieving accounts and transactions;
- registering users;
- adding and removing friends;
- sending events to Kafka topics `client-topic` and `account-topic`.

#### `bank-infrastructure`

Contains the infrastructure layer:

- JPA entities `User`, `Account`, `Transaction`;
- DTOs for API and events;
- Spring Data JPA repositories;
- enums for transaction statuses and types;
- custom exceptions and a global error handler.

#### `bank-presentation`

Contains REST controllers:

- `UserController`
- `AccountController`
- `BankApplication`

The main endpoints cover:

- users;
- user friends;
- user accounts;
- account creation;
- balance;
- transfers;
- transaction history;
- transaction filtering by type and account.

### 2. API Gateway

`API-Gateway` is a separate Spring Boot application that acts as an external entry point.

What the gateway does:

- authenticates users;
- generates JWTs;
- validates JWTs in the `JwtAuthFilter`;
- restricts access by roles;
- stores gateway accounts in the `gateway_users` table;
- stores revoked tokens in the `token_blacklist`;
- proxies calls to the main banking API through the `RestTemplate`.

The main components are:

- `AuthController`
- `AdminController`
- `ClientController`
- `AuthService`
- `AdminServices`
- `ClientServices`
- `JwtServices`
- `UserDetailsServiceImpl`
- `SecurityConfig`

Role access:

- **ADMIN** — creating administrators and clients, viewing users and accounts;
- **CLIENT** — viewing their own data, working with friends, depositing, withdrawing, and transferring.

### 3. Storage

`Storage` is a separate service for asynchronous event fixation.

It:

- connects to Kafka;
- reads messages from topics;
- saves events in PostgreSQL;
- stores events separately by clients and accounts.

Main components:

- `KafkaConsumer`
- `AccountEvent`
- `ClientEvent`
- `AccountEventRepository`
- `ClientEventRepository`

## Business logic features

### Transfers and commission

The `AccountService` implements a commission for transfers:

- **0%** — transfer to yourself;
- **3%** — transfer to a friend;
- **10%** — transfer to another user who is not in the friend list.

### Transactions

Supported operation types:

- `DEPOSIT`
- `WITHDRAW`
- `TRANSFER`

Supported transaction statuses:

- `PENDING`
- `SUCCESS`
- `FAILED`

### Users

A user contains:

- login;
- name;
- age;
- gender;
- hair color;
- list of friends;
- list of accounts.

## Technology stack

- **Java 23**
- **Spring Boot 3.4.4**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security 6**
- **PostgreSQL**
- **Kafka**
- **JWT (jjwt 0.12.6)**
- **Swagger / Springdoc OpenAPI**
- **MapStruct**
- **Lombok**
- **Maven**
- **Docker Compose**
- **GitHub Actions**

## Repository structure

```text
.
├── API-Gateway
├── Storage
├── bank-application
├── bank-infrastructure
├── bank-presentation
├── docker-compose.yml
└── pom.xml
```

The root `pom.xml` is an aggregating parent-project with modules:

- `bank-application`
- `bank-infrastructure`
- `bank-presentation`
- `API-Gateway`
- `Storage`

## Infrastructure and ports

### PostgreSQL and pgAdmin

In the root `docker-compose.yml`, the following are raised:

- **PostgreSQL** on `localhost:54321`
- **pgAdmin** on `localhost:8080`

DB parameters:

- database: `bank`
- user: `postgres`
- password: `postgres`

### Kafka

In `Storage/docker-compose.yml`, the following are raised:

- **Zookeeper** on `2181`
- **Kafka** on `9092`

### Application ports

- **bank-presentation** — `8081`
- **API-Gateway** — `8082`
- **Storage** — `8083`

## Security

Security is implemented in `API-Gateway`.

Implemented:

- stateless configuration via `SessionCreationPolicy.STATELESS`;
- login via `/api/auth/login`;
- JWT filter `JwtAuthFilter`;
- password encryption via `BCryptPasswordEncoder`;
- blacklist tokens on logout;
- separation of rights via `@PreAuthorize`.

The main protected zones are:

- `/api/admin/**` — only `ADMIN`;
- `/api/client/**` — only `CLIENT`.

## API documentation

Swagger/OpenAPI is included in:

- `bank-presentation`
- `API-Gateway`

Based on the configuration, the application publishes OpenAPI documentation and Swagger UI through `springdoc`.

## Event model

When changes are made in the system, two types of events are published:

- **Client events** — creation and modification of users;
- **Account events** — creation of accounts, modification of balances, and new operations.

The banking service publishes events to Kafka, and `Storage` stores them in the database. This allows for the separation of the main business logic from the audit layer and asynchronous storage of change history.

## Building and running

### 1. Clone the repository

```bash
git clone https://github.com/EpicWhal3/Java-projects.git
cd Java-projects
```

### 2. Start PostgreSQL and pgAdmin

```bash
docker compose up -d
```

### 3. Start Kafka and Zookeeper

```bash
cd Storage
docker compose up -d
cd ..
```

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the applications

In separate terminals:

#### bank-presentation

```bash
cd bank-presentation
mvn spring-boot:run
```

#### API-Gateway

```bash
cd API-Gateway
mvn spring-boot:run
```

#### Storage

```bash
cd Storage
mvn spring-boot:run
```

## CI

The repository has a GitHub Actions workflow configured at `.github/workflows/java.yml`, which builds the project via Maven on `push`.