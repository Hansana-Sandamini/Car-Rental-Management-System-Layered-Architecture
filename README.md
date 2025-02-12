# Car Rental Management System

A **Car Rental Management System** designed to manage car rentals, reservations, payments, customer details, and more. The system is built using **JavaFX** for the user interface and follows a **Layered Architecture** for better scalability, maintainability, and testability.

RDF Car Rentals System is designed to streamline the operations of a car rental business by providing a comprehensive and efficient solution for managing various business functions such as customer bookings, payments, vehicle inventory, driver 
assignments, and income reporting. There are petrol engine cars, diesel engine cars, hybrid engine cars and electric engine cars in here. The system aims to modernize traditional processes by leveraging technology to simplify tasks like car 
reservations, payments, and driver scheduling, while ensuring the overall operation is smooth and transparent. RDFCR efficiently assigns drivers to specific bookings when requested by customers. Currently, this company manages its
business manually. RDFCR has no idea about the income of this business. As the business use to store data manual methods, they forget pending customer payments sometimes.

## Features

- **Customer Management**: Add, edit, and delete customer details.
- **Vehicle Management**: Add, edit, and delete vehicles available for rent.
- **Reservation System**: Book and manage car reservations.
- **Driver Assignment**: Assign drivers to specific reservations.
- **Payment Management**: Handle payments, calculate dues, and manage transaction records.
- **Reporting**: Generate income reports based on reservation details.
- **Credit Management**: Handle payment credits and dues.

## Advantages of the solution

This is a safe way to store sensitive data. The details related to the business can find easily
and quickly by using this. RDFCR can manage cars in the business through the system.
Cashiers and admins can save details of customers as include a customer management part in
this solution. Cashiers no need to worry about the calculation, the system will do it. Admin
can see the income of the business clearly for any duration. If a pending payment exceeds the
deadline the system will show it to admins. So, the pending payments are not forgotten for
admins. Admins can get a clear idea about business from this from features like top 3 renting
cars, income, pending credits, etc.

## Architecture

The system is built using a **Layered Architecture**, consisting of four layers:

### 1. **Presentation Layer (UI Layer)**
   - Handles the user interface and user interactions.
   - Built using **JavaFX** to display information like customer details, reservations, payment forms, etc.
   - Receives user input and sends it to the **Business Layer** for processing.

### 2. **Business Layer**
   - Contains the core application logic.
   - Manages operations such as car reservations, payments, driver assignments, and income reports.
   - Implements business rules like calculating monthly income and checking vehicle availability.

### 3. **Persistence Layer**
   - Manages database interactions and CRUD operations.
   - Provides an abstraction for database access using DAOs (Data Access Objects).
   - Examples of components include **ReservationDAO**, **PaymentDAO**, **CustomerDAO**, etc.

### 4. **Data Layer**
   - Defines the database schema (tables, relationships, constraints).
   - Ensures data integrity and consistency.
   - Handles SQL queries, stored procedures, and database management tasks.

## Technologies Used

- **Java** (JDK 17)
- **JavaFX** (for the UI)
- **Maven** (for dependency management)
- **MySQL** (for database management)
- **JasperReports** (for generating reports)

