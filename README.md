# Running the Application

To run this application using Maven and Spring Boot, use the following commands:

1. **Build the project**:
   ```bash
   mvn clean install
   ```

2. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

Make sure that you have Maven and JDK 11 or higher installed on your system.


# API Documentation

## Overview
This API allows the creation, retrieval, update, and deletion of residents, residences, and condominiums. Below is a detailed description of each available endpoint.

## Base URL
```
http://localhost:8080
```

## Endpoints

### 1. Residents

#### 1.1 Create a New Resident
- **Endpoint**: `/v1/resident`
- **Method**: `POST`
- **Description**: Creates a new resident in the system.
- **Request Body**:
  ```json
  {
    "name": "string",
    "surname": "string",
    "type": "OWNER | TENANT"
  }
  ```
- **Responses**:
  - `201 OK`: Resident successfully created.
  - `400 BAD REQUEST`: Error in the request.

#### 1.2 List All Residents
- **Endpoint**: `/v1/resident`
- **Method**: `GET`
- **Description**: Returns all residents registered in the system, along with their details.
- **Responses**:
  - `200 OK`: List of residents successfully returned.

#### 1.3 Get a Resident by Name
- **Endpoint**: `/v1/resident/{nameResident}`
- **Method**: `GET`
- **Description**: Returns data about a specific resident and the condominium bill value.
- **Parameters**:
  - `nameResident` (path): Name of the resident.
- **Responses**:
  - `200 OK`: Resident data successfully returned.

#### 1.4 Update a Resident's Data
- **Endpoint**: `/v1/resident/{nameResident}`
- **Method**: `PUT`
- **Description**: Updates the surname or type of a resident.
- **Parameters**:
  - `nameResident` (path): Name of the resident.
- **Request Body**:
  ```json
  {
    "surname": "string",
    "type": "OWNER | TENANT"
  }
  ```
- **Responses**:
  - `200 OK`: Data successfully updated.
  - `404 NOT FOUND`: Resident not found.

#### 1.5 Delete a Resident
- **Endpoint**: `/v1/resident/{nameResident}`
- **Method**: `DELETE`
- **Description**: Deletes a resident and the associated residence.
- **Parameters**:
  - `nameResident` (path): Name of the resident.
- **Responses**:
  - `200 OK`: Resident successfully deleted.

### 2. Residences

#### 2.1 Create a New Residence
- **Endpoint**: `/v1/residence`
- **Method**: `POST`
- **Description**: Creates a new residence in the system.
- **Request Body**:
  ```json
  {
    "number": "integer",
    "type": "HOUSE | APARTMENT",
    "area": "number",
    "residentName": "string",
    "condominiumName": "string"
  }
  ```
- **Responses**:
  - `200 OK`: Residence successfully created.

### 3. Condominiums

#### 3.1 Create a New Condominium
- **Endpoint**: `/v1/condominium`
- **Method**: `POST`
- **Description**: Creates a new condominium in the system.
- **Request Body**:
  ```json
  {
    "name": "string",
    "address": "string"
  }
  ```
- **Responses**:
  - `200 OK`: Condominium successfully created.

#### 3.2 Add an Expense to a Condominium
- **Endpoint**: `/v1/condominium/add_expense/{expenseValue}`
- **Method**: `POST`
- **Description**: Adds an expense to a registered condominium.
- **Parameters**:
  - `expenseValue` (path): Value of the expense.
  - `condominiumName` (header): Name of the condominium.
- **Responses**:
  - `200 OK`: Expense successfully added.

#### 3.3 Get Total Area of a Condominium
- **Endpoint**: `/v1/condominium/totalarea/{condominiumName}`
- **Method**: `GET`
- **Description**: Returns the total area of a condominium.
- **Parameters**:
  - `condominiumName` (path): Name of the condominium.
- **Responses**:
  - `200 OK`: Total area of the condominium successfully returned.

## Data Schemas

### ResidentPost
```json
{
  "name": "string",
  "surname": "string",
  "type": "OWNER | TENANT"
}
```

### ResidentPut
```json
{
  "surname": "string",
  "type": "OWNER | TENANT"
}
```

### ResidencePost
```json
{
  "number": "integer",
  "type": "HOUSE | APARTMENT",
  "area": "number",
  "residentName": "string",
  "condominiumName": "string"
}
```

### Resident
```json
{
  "residence": {
    "id": "uuid",
    "number": "integer",
    "type": "HOUSE | APARTMENT",
    "area": "number",
    "idealFraction": "number",
    "condominiumValue": "number"
  },
  "name": "string",
  "surname": "string",
  "type": "OWNER | TENANT"
}
```

### CondominiumPost
```json
{
  "name": "string",
  "address": "string"
}
```

## Final Note
This document describes all current functionalities of the API. For more details on how it works or for contributions, please refer to the repository's source code.

## Additional Resources

- **Postman Collection**: The directory contains a Postman collection with all available endpoints, named `ResidentialApp.postman_collection.json`.
- **API Contract**: The directory also includes the API contract in OpenAPI format, named `contract_api.yaml`.
