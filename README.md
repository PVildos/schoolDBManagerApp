SchoolDBManagerApp — Project Documentation

Repository: PVildos/schoolDBManagerApp
Language: Java
Build Tool: Maven
Type: CLI School Database Management System
Purpose: To manage core school data via a Java application.

📌 1. Project Overview

SchoolDBManagerApp is a Java-based application designed to help schools or educational institutes manage core data elements, such as:

teacher school placements, working hours, school availabilities, and more.

It’s structured as a Maven project, meaning dependencies and build configuration are defined inside pom.xml.

🧰 2. Architecture & Technology Stack
Component	Technology
Language	Java
Build & Dependency Management	Maven (pom.xml)
Data Persistence	Relational database (PostgreSQL)

📂 3. Project Structure

A typical structure for this kind of project looks like this:

schoolDBManagerApp/
├── src/
│   ├── main/
│   │   ├── java/          # Java application source
│   │   └── resources/     # Config files (e.g., application.properties)
├── pom.xml                # Maven build and dependency config
├── README.md              # Project description & usage

🛠️ 4. Installation & Setup
Prerequisites

Before running the app you’ll need:

Java (JDK 23 or newer)

Maven

A relational database (MySQL, SQLite, etc.) if the app persists to a real DB

➤ Step 1 — Clone the Repository
git clone https://github.com/PVildos/schoolDBManagerApp.git
cd schoolDBManagerApp

➤ Step 2 — Build With Maven
mvn clean install

This will:

download dependencies, compile code, optionally package into a runnable JAR/WAR.

➤ Step 4 — Run the Application

java -jar target/schoolDBManagerApp.jar
