# Screentime Monitor

A Java Spring Boot application that tracks and displays the screentime spent on each application or window on a Windows computer. It uses JNA to monitor active applications, logs screentime data, and provides a UI and REST API to view metrics.

## Overview

- **Track Active Applications**: Utilizes JNA (Java Native Access) to call Windows API functions and identify the currently active application or window title.
- **Record Screentime**: Periodically logs the time spent on each application or window.
- **Expose Metrics**: Provides a Thymeleaf-based UI and a custom REST API to display screentime data.
- **Installable App**: Can be packaged as a JAR or EXE for easy deployment on Windows.

## Step 1: Project Setup

### Prerequisites

- **Java Development Kit (JDK)**: Version 17 or later installed.
- **Maven**: Used as the build tool (Gradle is also an option).
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code recommended.

### Initial Setup

1. Create a Spring Boot project with dependencies: `Spring Web`, `Spring Boot Actuator`, `Thymeleaf`, `Spring Data JPA`, `H2 Database`, and `JNA`.
2. Configure the project using Maven (see `pom.xml` for details).

### Run the Application

1. In your IDE, run the main class: `ScreentimeMonitorApplication.java`.
2. Open a browser or Postman and visit `http://localhost:8080/` to view the Thymeleaf UI, or `http://localhost:8080/screentime` for raw metrics via the REST API.

## Step 5: Make It Installable

### Package as JAR

1. Run the following command to build the JAR:
   ```bash
   mvn clean package
   ```
2. Locate the JAR in the `target/` folder (e.g., `screentime-monitor-0.0.1-SNAPSHOT.jar`).
3. Run it on Windows:
   ```bash
   java -jar screentime-monitor-0.0.1-SNAPSHOT.jar
   ```
### Optional: Create an EXE

- Use [Launch4j](http://launch4j.sourceforge.net/):
  1. Configure Launch4j to wrap the JAR into an `.exe`.
  2. Set it to run in the background (no console).
- Alternatively, use [Inno Setup](https://jrsoftware.org/isinfo.php) to create an installer that bundles the JAR and a startup script.

### Run on Startup

- Add the JAR or EXE to the Windows Startup folder or registry:
  - **Startup Folder**: `C:\Users\<YourUser>\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup`.
  - Place the JAR/EXE or a shortcut there to launch on system boot.

## Features

- **Real-Time Tracking**: Monitors active windows every second using JNA.
- **Granular Metrics**: Tracks screentime by window title (e.g., specific Chrome tabs or app instances).
- **Persistent Data**: Stores screentime data in an H2 database to retain metrics across restarts.
- **User Interface**: Displays a table of screentime metrics via a Thymeleaf-generated webpage at `http://localhost:8080/`.

## Usage

1. Start the application using your IDE or the packaged JAR/EXE.
2. Use your computer normally—open applications, switch windows, etc.
3. Visit `http://localhost:8080/` in a browser to see a table of screentime per application/window, updated in real-time.
