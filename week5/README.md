## Docker Setup – Week 5 Lab 5

### Project

Books API – Spring Boot REST API containerised using Docker, with PostgreSQL as the database.

### Docker Image

- Docker Hub repository: [aruljothee/books-api](https://hub.docker.com/r/aruljothee/books-api)
- Image tag: `1.2`

### Build and Run

```powershell
docker build -t books-api:1.2 .
docker run -d --name books-api-container -p 8080:8080 books-api:1.2
```

### PostgreSQL and Persistent Storage

The PostgreSQL database runs in a separate container. Both containers communicate through the `books-network` Docker network. The `books-data` named volume preserves database data when the database container is recreated.

### Verification Commands

```powershell
docker ps
docker logs --tail 30 books-api-container
docker exec -it books-api-container sh
docker exec -it db psql -U booksuser -d books_db -c "SELECT * FROM books;"
curl.exe http://localhost:8080/api/books
docker volume ls
docker network inspect books-network
```

### Docker Hub

```powershell
docker login
docker tag books-api:1.2 aruljothee/books-api:1.2
docker push aruljothee/books-api:1.2
```

### Outcome

- Built and ran the Books API Docker image.
- Connected the API to PostgreSQL using a custom Docker network.
- Verified database persistence using a named volume.
- Inspected containers and logs using Docker CLI.
- Published the API image to Docker Hub.
