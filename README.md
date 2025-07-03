# Operator Management System

This project is a terminal-based application for managing Aotearoa New Zealand activity operators. Developed in Java, it provides a command-line interface (CLI) for administrators to manage operators, their activities, and various types of reviews. The system is designed with a strong focus on Object-Oriented Programming (OOP) principles, including encapsulation, inheritance, and polymorphism.

## Table of Contents
- [Features](#features)
- [Object-Oriented Design](#object-oriented-design)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [Running the Application](#running-the-application)
  - [Running Tests](#running-tests)
- [System Commands](#system-commands)
- [Project Structure](#project-structure)

## Features

- **Operator Management**: Register new operators with a name and location, and search for existing operators.
- **Activity Management**: Create and assign activities to operators, and view or search for activities across the system.
- **Multi-faceted Review System**:
    - Add **Public Reviews** from customers, which can be endorsed by administrators.
    - Add **Private Reviews** for direct feedback to operators, which can be resolved with a response.
    - Add **Expert Reviews** from field specialists, which can include image uploads.
- **Data Aggregation**: Display the top-rated activity for each location, calculated from public and expert reviews.
- **Bilingual Location Support**: Recognizes location names in both English and te reo Māori.

## Object-Oriented Design

This system is built following key OOP principles to ensure a modular, scalable, and maintainable codebase.

- **Encapsulation**: Data for operators, activities, and reviews are encapsulated within their respective classes, with access controlled through public methods. The core logic is managed by the `OperatorManagementSystem` class, which orchestrates interactions between different model objects.

- **Inheritance & Polymorphism**: To handle the different types of reviews, an `abstract class` `Review` was created. This base class defines common attributes and methods for all reviews (e.g., review ID, rating, review text).
    - Three concrete subclasses extend `Review`:
        - `PublicReview`: Adds functionality for anonymity and endorsements.
        - `PrivateReview`: Adds fields for customer email and follow-up resolution.
        - `ExpertReview`: Adds fields for recommendations and image uploads.
    - This structure allows the system to treat all reviews polymorphically (e.g., storing them in a single collection) while still being able to invoke type-specific behavior.

- **Class Structure**: Key classes in the design include:
    - `Operator`: Represents an activity operator, holding its details and a list of its activities.
    - `Activity`: Represents an activity, holding its details and a list of its reviews.
    - `Review` (abstract): The base class for all review types.
    - `PublicReview`, `PrivateReview`, `ExpertReview`: Concrete implementations of `Review`.

## Technologies Used

- **Java**: The core programming language for the application logic.
- **Maven**: For project build management, dependency handling, and running the application and tests.
- **JUnit**: For unit testing the application's functionality.

## Getting Started

Follow these instructions to get a local copy of the project up and running.

### Prerequisites

- Java Development Kit (JDK) 11 or higher.
- Apache Maven.

### Installation

1. Clone the GitHub repository to your local machine:
   ```sh
   git clone https://github.com/your-github-username/your-repository-name.git

 ## Usage

## Usage

| Task                  | Windows Command                       | macOS / Linux Command              |
| --------------------- | ------------------------------------- | ---------------------------------- |
| **Run Application**   | `.\mvnw.cmd compile exec:java@run`    | `./mvnw compile exec:java@run`     |
| **Run Tests**         | `.\mvnw.cmd clean compile test`       | `./mvnw clean compile test`        |
