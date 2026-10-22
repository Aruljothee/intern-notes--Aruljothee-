# Books API - Docker Lab 5

## Project Description

A Spring Boot REST API for managing books using PostgreSQL and Docker.

## Docker Hub Image

Repository: [aruljothee/books-api](https://hub.docker.com/r/aruljothee/books-api)

Image tag: `1.2`

## Build Docker Image

```bash
docker build -t books-api:1.2 .
```

## Run the Application

```bash
docker run -d --name books-api-container -p 8080:8080 books-api:1.2
```

## Test the API

```bash
curl http://localhost:8080/api/books
```

## Docker Commands

List running containers:

```bash
docker ps
```

View application logs:

```bash
docker logs books-api-container
```

Open a shell inside the container:

```bash
docker exec -it books-api-container sh
```

## Push Image to Docker Hub

```bash
docker tag books-api:1.2 aruljothee/books-api:1.2
docker push aruljothee/books-api:1.2
```

## Pull Image from Docker Hub

```bash
docker pull aruljothee/books-api:1.2
```
