# 🚗 AutoPark Inventory Management System

![Java](https://img.shields.io/badge/Java-25-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-GUI-blue)
![Maven](https://img.shields.io/badge/Maven-3.9.9-C71A36)
![JUnit 5](https://img.shields.io/badge/JUnit_5-39_Tests_Passed-25A162)

AutoPark is a Java-based vehicle dealership inventory management application built using **JavaFX and Object-Oriented Programming (OOP)**. It provides an interactive graphical interface for managing vehicle and tire inventories, handling customer purchases, and tracking sales.

The project focuses on applying object-oriented design principles to build a structured and maintainable application, with JUnit tests used to verify its functionality.

## Features

- **Vehicle Inventory Management:** Manage different types of vehicles, including sedans, SUVs, trucks, and vans.
- **Tire Inventory:** View and manage available tires alongside the vehicle inventory.
- **Shopping Cart:** Add and remove items from a shopping cart before completing a purchase.
- **Sales Tracking:** Process purchases and maintain sales information.
- **Graphical User Interface:** Interact with the inventory and sales system through a JavaFX interface.
- **Unit Testing:** Test application functionality using JUnit 5.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core application logic and object-oriented programming |
| JavaFX | Graphical user interface |
| Apache Maven | Project building and dependency management |
| JUnit 5 | Automated unit testing |

**Programming Concepts:** Inheritance, Polymorphism, Encapsulation, Abstraction, Event-Driven Programming, and Unit Testing.

## Project Structure

```text
AutoPark-Inventory-Management-System/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── AutoPark.java
│   │       ├── AutoParkApp.java
│   │       ├── AutoParkView.java
│   │       ├── CommercialVehicle.java
│   │       ├── Item.java
│   │       ├── JavaFXTest.java
│   │       ├── PersonalVehicle.java
│   │       ├── Sedan.java
│   │       ├── SUV.java
│   │       ├── Tire.java
│   │       ├── Truck.java
│   │       ├── Van.java
│   │       └── Vehicle.java
│   └── test/
│       └── java/
│           ├── AutoParkTest.java
│           ├── AutoParkViewTest.java
│           ├── ItemTest.java
│           ├── TireTest.java
│           └── VehicleTest.java
├── .gitignore
└── README.md
```

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- Java Development Kit (JDK)
- Apache Maven
- IntelliJ IDEA or Visual Studio Code with Java extensions

### Version Compatibility

The project was successfully compiled and tested using the following environment:

| Tool | Tested Version |
|---|---|
| Java JDK | 25 LTS |
| Apache Maven | 3.9.9 |
| JUnit 5 | 5.14.0 |
| Operating System | Windows 11 |

**Note:** For the most consistent results, use the same Java and Maven versions listed above. Ensure that the JavaFX and JUnit dependencies are compatible with your installed JDK and match the versions configured in `pom.xml`.

Using different versions may require additional configuration or produce compatibility warnings.

### Installation

**1. Clone the repository**

```bash
git clone https://github.com/LavenGit/AutoPark-Inventory-Management-System.git
```

**2. Navigate into the project directory**

```bash
cd AutoPark-Inventory-Management-System
```

**3. Compile the project**

```bash
mvn clean compile
```

Maven will automatically download the dependencies declared in `pom.xml`.

## Running the Application

The JavaFX graphical application is implemented in `AutoParkApp.java`.

To run the application:

1. Open the project in IntelliJ IDEA or Visual Studio Code.
2. Import the project as a Maven project using `pom.xml`.
3. Allow Maven to resolve the required dependencies.
4. Locate `AutoParkApp.java` inside `src/main/java`.
5. Run the application's main entry point with JavaFX configured.

The application provides a graphical interface for managing inventory, shopping cart operations, and sales.

## ✅ Running Unit Tests

The project uses **JUnit 5** to verify the functionality of the inventory management system.

**Run all unit tests:**

```bash
mvn clean test
```

**Run a specific test class:**

```bash
mvn -Dtest=AutoParkTest test
```

### Test Results

All 39 unit tests passed successfully during local testing.

```text
Tests run: 39, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

The test suite includes:

- `AutoParkTest.java`
- `AutoParkViewTest.java`
- `ItemTest.java`
- `TireTest.java`
- `VehicleTest.java`

These tests help verify inventory operations and other application functionality.

## Additional Notes

- Maven manages the project's dependencies and build process through `pom.xml`.
- The `target/` directory contains generated build files and is excluded from version control.
- JavaFX and Maven may display compatibility warnings when using newer Java versions. These warnings do not necessarily indicate errors.
- For the most consistent setup, use compatible Java, JavaFX, Maven, and JUnit versions.
