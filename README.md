# Aerospike Local Environment Setup

This guide details the steps to set up a local Aerospike Database instance using Docker. It specifically addresses common connectivity issues on Windows/WSL2 environments.

## 1. Prerequisites
* **Docker Desktop** (Windows/Mac) or **Docker Engine** (Linux) installed.
* **Java JDK 8+** (for the client application).
* **Maven** (for dependency management).

---

## 2. Troubleshooting: "Docker Daemon Not Running"
If you encounter the error:
> `docker: error during connect: Head "http://%2F%2F.%2Fpipe%2FdockerDesktopLinuxEngine/_ping": open //./pipe/dockerDesktopLinuxEngine: The system cannot find the file specified.`

**Follow these steps to fix it:**

1.  **Launch Docker Desktop:** Press the `Windows Key`, type "Docker Desktop", and open the application.
2.  **Wait for Initialization:** Look at the system tray (bottom right). Wait until the Docker "whale" icon stops animating and remains steady.
3.  **Verify via Terminal:** Open your terminal (Command Prompt or PowerShell) and run:
    ```bash
    docker version
    ```
    *Success Output:* You should see details for both `Client:` and `Server:`.

---

## 3. Installation & Run
Once Docker is active, run the following command to download and start the Aerospike Community Edition server.

```bash
docker run -d --name aerospike -p 3000:3000 -p 3001:3001 -p 3002:3002 aerospike/aerospike-server
```

Verify it is running: 
```bash
docker ps
```

# Application Build & Deployment Guide

This section details how to build the Spring Boot application, run it locally, and test the APIs using cURL commands.

## 4. Build & Run Application

### Step 1: Build the Project
This command downloads all necessary dependencies and compiles your code into an executable artifact.

```bash
mvn clean install
```
Note: If the build fails due to connection issues during testing, ensure your Docker container is running. You can also skip tests by adding -DskipTests to the command.

Step 2: Run the Application
The fastest way to run the app during development (avoiding the need to package a JAR every time) is using the Spring Boot Maven plugin:

Bash
```bash
mvn spring-boot:run
```
Success Indicator: Look for logs ending with Started AerospikeApplication in X seconds and confirmation that the server is listening on port 8080.

## 5. Test the API (cURL Commands)
Use the following commands to interact with your running application via the terminal.

### Create a User (POST)
For Windows (Command Prompt / PowerShell):
Note: Windows requires double quotes for JSON and escaped internal quotes \".

```text
curl -X POST http://localhost:8080/api/users ^
 -H "Content-Type: application/json" ^
 -d "{\"id\": \"user_500\", \"name\": \"Curl Master\", \"email\": \"curl@test.com\", \"experience\": 10}"
```


### Get a User (GET)
For All Platforms:

```text
curl http://localhost:8080/api/users/user_500
```
