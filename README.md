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