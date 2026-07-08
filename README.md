# Ghost Net Fishing

Java EE web application for reporting, managing and recovering ghost nets.  
This project was developed as part of a university case study.

## Description

Ghost Net Fishing is a web application that supports the management of lost fishing nets (ghost nets). Users can report discovered ghost nets, update their status and register recovery information.

The application provides functions for:
- reporting new ghost nets with GPS coordinates and estimated size
- displaying registered ghost nets
- filtering ghost nets requiring recovery
- assigning recovering persons to ghost nets
- updating the recovery status
- deleting entries

## Technologies

The application was developed using:

- Java
- Jakarta EE
- Jakarta Server Faces (JSF)
- PrimeFaces
- Jakarta Persistence API (JPA)
- MySQL
- Maven
- Apache TomEE

## Architecture

The application follows a layered architecture:

- Presentation layer: JSF pages and PrimeFaces components
- Controller layer: Managed Bean for user interaction
- Service layer: Business logic and database operations
- Persistence layer: JPA entities mapped to a MySQL database

## Main Components

- `GhostNetController` – handles user interactions
- `GhostNetService` – manages business logic and persistence operations
- `GhostNet` – entity representing a reported ghost net
- `BergendePerson` – entity representing a recovering person
- `GhostNetStatus` – enum representing the current status of a ghost net

## Database

The application uses a relational MySQL database containing tables for:

- ghost nets
- recovering persons

The relationship between both entities is implemented using JPA.

## License

This repository was created for educational purposes as part of a university project.
