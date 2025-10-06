# Counterparty API

A Spring Boot 3.x REST API for managing counterparties with Microsoft SQL Server integration.

## Features

- **POST /api/v1/counterparty/create-counterparty** - Create a new counterparty
- Full validation of required fields
- Duplicate checking based on client ID, party name, and account details
- Complete audit trail with JSON request storage
- Global exception handling

## Prerequisites

- Java 17
- Maven 3.6+
- Microsoft SQL Server
- Spring Boot 3.5.6

## Database Setup

1. Create the database and table using the provided SQL:

```sql
CREATE DATABASE usbc_db;

USE usbc_db;

CREATE TABLE counterparties (
  cp_seq_id INT IDENTITY(1,1) PRIMARY KEY,
  counterparty_id VARCHAR(36) NOT NULL,
  client_id NVARCHAR(140) NOT NULL,
  alternate_party_id NVARCHAR(140),
  party_type NVARCHAR(15) NOT NULL,
  party_name NVARCHAR(140) NOT NULL,
  party_nick_name NVARCHAR(140),
  party_org_id NVARCHAR(35),
  party_private_id NVARCHAR(35),
  party_account_iban NVARCHAR(30),
  party_account_other_id NVARCHAR(34),
  party_account_currency NVARCHAR(3),
  party_addr_department NVARCHAR(70),
  party_addr_sub_department NVARCHAR(70),
  party_addr_street_name NVARCHAR(70) NOT NULL,
  party_addr_building_no NVARCHAR(16) NOT NULL,
  party_addr_building_name NVARCHAR(35),
  party_addr_floor NVARCHAR(70),
  party_addr_town_location_name NVARCHAR(35),
  party_addr_district_name NVARCHAR(35),
  party_addr_subdivision NVARCHAR(35) NOT NULL,
  party_addr_country NVARCHAR(2) NOT NULL,
  party_phone_number NVARCHAR(30),
  party_email NVARCHAR(2048),
  agent_name NVARCHAR(140) NOT NULL,
  agent_clearing_sys_member_code NVARCHAR(15),
  agent_bic NVARCHAR(11),
  agent_clearing_sys_member_id NVARCHAR(35),
  agent_addr_department NVARCHAR(70),
  agent_addr_sub_department NVARCHAR(70),
  agent_addr_street_name NVARCHAR(70),
  agent_addr_building_no NVARCHAR(16),
  agent_addr_building_name NVARCHAR(35),
  agent_addr_floor NVARCHAR(70),
  agent_addr_post_box NVARCHAR(16),
  agent_addr_room NVARCHAR(70),
  agent_addr_postal_code NVARCHAR(16),
  agent_addr_town_name NVARCHAR(35),
  agent_addr_town_location_name NVARCHAR(35),
  agent_addr_district_name NVARCHAR(35),
  agent_addr_subdivision NVARCHAR(35) NOT NULL,
  agent_addr_country NVARCHAR(2) NOT NULL,
  agent_phone_number NVARCHAR(30),
  agent_email NVARCHAR(2048),
  created_by NVARCHAR(140) NOT NULL,
  approved_by NVARCHAR(140) NOT NULL,
  last_modified_by NVARCHAR(140),
  created_datetime DATETIME2 NOT NULL,
  last_modified_datetime DATETIME2,
  json_request NVARCHAR(MAX) NOT NULL,
  enabled BIT NOT NULL
);

CREATE UNIQUE INDEX unq_cp_id_name_iban_acct_oth_id
  ON counterparties (client_id, party_name, party_account_iban, party_account_other_id)
  WHERE enabled = 1;
```

2. Update `application.yml` with your SQL Server connection details:

```yaml
spring:
  datasource:
    url: jdbc:sqlserver://localhost:1433;databaseName=usbc_db
    username: your_username
    password: your_password
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
```

## Running the Application

1. Clone the repository
2. Update database configuration in `src/main/resources/application.yml`
3. Run the application:

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Usage

### Create Counterparty

**Endpoint:** `POST /api/v1/counterparty/create-counterparty`

**Request Body Example:**

```json
{
  "messageId": "MSG123456",
  "traceabilityId": "TRACE89765",
  "clientId": "USC12345",
  "alternatePartyId": "usbcCorpID1",
  "partyType": "Debtor",
  "partyName": "Acme Corporation",
  "partyNickName": "Acme",
  "partyOrganizationId": "ORG-2025",
  "partyPrivateId": "PRIV-777",
  "partyAccount": {
    "iban": "US12ABCD123456789012345678901",
    "otherId": "Accnt-1234",
    "currency": "USD"
  },
  "partyAddress": {
    "department": "dept1",
    "subDepartment": "subdept1",
    "streetName": "Main Street",
    "buildingNumber": "1234",
    "buildingName": "Acme Towers",
    "floor": "19",
    "townLocationName": "Manhattan",
    "districtName": "District A",
    "subdivision": "Subdivision 1",
    "country": "US",
    "email": "john.doe@tds.com"
  },
  "agent": {
    "agentName": "Clearing Agent A",
    "identification": {
      "clearingSystemMemberCode": "USABA",
      "clearingSystemMemberId": "MEM-1234",
      "bic": "NRTHUS33"
    },
    "address": {
      "department": "dept1",
      "streetName": "Street X",
      "country": "US",
      "email": "agent@tds.com"
    }
  },
  "createdBy": "AB",
  "approvedBy": "admin"
}
```

**Success Response (201 Created):**

```json
{
  "status": "SUCCESS",
  "message": "Counterparty for ClientId USC12345 created successfully",
  "counterpartyId": "550e8400-e29b-41d4-a716-446655440000"
}
```

**Error Response (400 Bad Request):**

```json
{
  "status": "ERROR",
  "message": "Counterparty already exists for ClientId USC12345 with PartyName Acme Corporation and account details",
  "counterpartyId": null
}
```

## Validation Rules

- **Required fields:** `clientId`, `partyName`, `partyAccount`, `partyAddress`, `agent`, `createdBy`, `approvedBy`
- **Account validation:** At least one of `iban` or `otherId` must be provided in `partyAccount`
- **Uniqueness:** Combination of `clientId`, `partyName`, and account details (`iban`/`otherId`) must be unique when `enabled=true`

## Project Structure

```
src/main/java/com/gtbcm/party/
├── controller/
│   └── CounterpartyController.java
├── dto/
│   ├── ApiResponse.java
│   ├── CounterpartyRequest.java
│   ├── PartyAccount.java
│   ├── PartyAddress.java
│   ├── Agent.java
│   ├── AgentAddress.java
│   └── AgentIdentification.java
├── entity/
│   └── CounterpartyEntity.java
├── exception/
│   ├── DuplicateCounterpartyException.java
│   └── GlobalExceptionHandler.java
├── repository/
│   └── CounterpartyRepository.java
├── service/
│   └── CounterpartyService.java
└── CounterpartyApiApplication.java
```

## Technologies Used

- Spring Boot 3.5.6
- Spring Data JPA
- Spring Web
- Spring Validation
- Microsoft SQL Server JDBC Driver
- Lombok
- Jackson (JSON processing)
- Java 17
