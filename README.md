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

---

## 4. Build & Run Application

### Step 1: Build the Project
This command downloads all necessary dependencies and compiles your code into an executable artifact.

```bash
mvn clean install
```
Note: If the build fails due to connection issues during testing, ensure your Docker container is running. You can also skip tests by adding -DskipTests to the command.

Step 2: Run the Application
The fastest way to run the app during development (avoiding the need to package a JAR every time) is using the Spring Boot Maven plugin:

Option 1 :
Bash
```text
mvn spring-boot:run "-Dspring-boot.run.jvmArguments=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```
Option 2 : 
Bash
```text
mvn spring-boot:run
```
Success Indicator: Look for logs ending with Started AerospikeApplication in X seconds and confirmation that the server is listening on port 8080.

---


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

---


# 6. Docker Troubleshooting: Container Name Conflicts

## The Scenario
You attempt to spin up a new Aerospike container using `docker run`, but the command fails with a naming conflict error.

**Command Run:**
```bash
docker run -d --name aerospike -p 3000:3000 -p 3001:3001 -p 3002:3002 aerospike/aerospike-server
```

**Error Received:**
```text
docker: Error response from daemon: Conflict. The container name "/aerospike" is already in use by container "abcd...". You have to remove (or rename) that container to be able to reuse that name.
```

**Root Cause**
```text
Docker containers have a lifecycle. Even when a container is stopped (and disappears from docker ps), it still exists on disk in an "Exited" state.

Docker prevents you from creating a new container with the name aerospike because a stopped container with that name already exists.
```


**Solutions**
### Option 1: The "Fresh Start" (Recommended)

Use this if: You want a clean slate, or you are unsure if the old container had the correct port mappings. This deletes the old container and creates a brand new one.

Remove the old container:

```text
docker rm aerospike
```
Run the new container:

```text
docker run -d --name aerospike -p 3000:3000 -p 3001:3001 -p 3002:3002 aerospike/aerospike-server
```
### Option 2: The "Wake Up"
Use this if: The old container was working fine previously, and you just want to resume it (preserving any data stored inside it).

Start the existing container:

```text
docker start aerospike
```
**Verification**

After applying one of the fixes above, verify the database is running and accessible.

**Check Process Status:**

```text
docker ps

Look for: aerospike in the NAMES column.
Look for: Up X seconds in the STATUS column.
```
---

# 7. Local Development & Debugging Guide

You have two ways to run the app. Choose Option A if you need to debug.

## Debugging in IntelliJ IDEA

Follow these steps to attach the debugger and inspect your code line-by-line.

### Step 1: Start the Debugger
1.  **Open the Project:** Launch IntelliJ IDEA and ensure your Maven project is loaded.
2.  **Open Main Class:** Navigate to your main application file (e.g., `AerospikeDemoApplication.java`).
3.  **Launch Debug Mode:** Right-click anywhere in the code editor window and select **Debug 'AerospikeDemoApplication'**.
    * *Look for the green bug icon*.

### Step 2: Inspecting the Code
1.  **Set Breakpoints:** Click the **gutter** (the gray space to the left of the line numbers) next to the line of code you want to stop at.
    * *A red dot will appear indicating the breakpoint is active.*
2.  **Trigger the Logic:** Send a request to your application using **Postman**, **cURL**, or a browser.
3.  **Debug:** The IDE will automatically pause execution when it hits your breakpoint, allowing you to inspect variables and step through the code.

