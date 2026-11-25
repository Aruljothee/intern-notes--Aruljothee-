\# College Leave Management System



A web-based College Leave Management System built using Spring Boot, React, PostgreSQL, Keycloak, and Docker.



\## Technologies Used



\- Java 17

\- Spring Boot

\- React.js

\- PostgreSQL 17

\- Keycloak 26.8.1

\- Docker and Docker Compose

\- Swagger / OpenAPI



\## Project Structure



```text

College-Leave-Management/

├── backend/

├── frontend/

├── keycloak/

│   └── realm-import/

│       └── campusleave-realm.json

├── .gitignore

├── .env.example

└── docker-compose.yml

```



\## Prerequisites



Install the following:



\- Docker Desktop

\- Git



\## Setup Instructions



\### 1. Clone the repository



```bash

git clone https://github.com/Aruljothee/intern-notes--Aruljothee-.git

```



\### 2. Navigate to the project folder



```bash

cd intern-notes--Aruljothee-/College-Leave-Management

```



\### 3. Configure environment variables



Copy `.env.example` to `.env` and update the values with your own credentials.



\### 4. Build and start the application



```bash

docker compose up -d --build

```



\### 5. Check container status



```bash

docker compose ps

```



\## Application URLs



| Component | URL |

|---|---|

| Frontend | http://localhost:5173 |

| Backend | http://localhost:8080 |

| Swagger UI | http://localhost:8080/swagger-ui/index.html |

| Keycloak | http://localhost:8180 |



\## Stop the application



```bash

docker compose down

```



\## Note



\- Never commit your `.env` file or actual passwords to GitHub.

\- A fresh installation requires its own database and Keycloak user setup.

\- Configure the required environment variables before starting Docker Compose.

