# Solance Workflow Service – Implementation Summary

## Technology Stack
- Java 17, Spring Boot 3
- Spring Data JPA (H2 in-memory database)
- Spring Boot Actuator (health, metrics)
- Springdoc OpenAPI (Swagger UI)
- JUnit 5 (unit & integration tests)
- Docker and Docker Compose

## Features Implemented
- REST API for workflow instructions:
  - Register customer
  - Open customer account
  - Create deposit transaction (pay-in)
  - Create payment instruction (pay-out)
- DTOs: Immutable records for input/output
- JPA entity for instructions, repository for persistence
- Service layer for business logic, validation, and DTO mapping
- Strategy pattern for instruction processing (one handler per type)
- InstructionHandlerFactory: Dispatches to the correct handler
- Global exception handler for consistent JSON error responses
- OpenAPI/Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- Actuator: [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

## API Endpoints
- `POST /api/instructions` — Submit a new instruction
- `GET /api/instructions` — List all instructions
- `GET /api/instructions?type=DEPOSIT` — Filter by type
- `PUT /api/instructions/{id}/process` — Manually process an instruction

## Project Structure
- `controller/InstructionController.java` — REST endpoints
- `service/InstructionService.java` — Business logic
- `entity/Instruction.java`, `InstructionType.java`, `InstructionStatus.java` — JPA model
- `repository/InstructionRepository.java` — Data access
- `dto/InstructionDto.java`, `InstructionResponseDto.java` — Request/response records
- `handler/InstructionHandler.java` — Handler interface
- `handler/InstructionHandlerFactory.java` — Handler dispatcher
- `handler/handlers/` — Handlers for each instruction type:
    - `DepositHandler.java`, `PaymentHandler.java`, `OpenAccountHandler.java`, `RegisterCustomerHandler.java`, `NoOpHandler.java`
- `handler/register/` — Register customer service:
    - `RegisterCustomerService.java` (interface)
    - `DefaultRegisterCustomerService.java` (implementation)
- `exception/GlobalExceptionHandler.java` — Centralized error handling

### Handler Package Structure
- All instruction type handlers are in `handler/handlers/`.
- The register customer service abstraction and implementation are in `handler/register/`.
- The factory (`InstructionHandlerFactory`) wires all handlers and is in `handler/`.

## Testing
- Unit tests for all handlers (`handler/handlers/`), register service (`handler/register/`), and the factory (`handler/InstructionHandlerFactoryTest.java`)
- Integration tests for controller endpoints (`controller/InstructionControllerTest.java`, `InstructionControllerIntegrationTest.java`)
- Sample scenarios for each handler and service

## Running the Service
1. Build: `mvn clean install`
2. Run: `mvn spring-boot:run`


## Containerization
- Dockerfile: Build a containerized Spring Boot app (OpenJDK 17)
- docker-compose.yml: Run the app on port 8080

## Extending & Hardening
- Add authentication/authorization (Spring Security)
- Use a persistent database (e.g., PostgreSQL) for production
- Add message queue integration for async processing
- Implement distributed tracing and advanced monitoring
- Harden validation and error handling for edge cases
- Add more comprehensive integration and load tests
- Introduce asynchronous message processing (e.g., via a message queue like AWS SQS or Kafka) to decouple API ingestion from processing, enabling higher throughput, retries, and resilience.

---

*This README documents only what is actually implemented in this repository. See `requirement.txt` for the original challenge brief.* 
