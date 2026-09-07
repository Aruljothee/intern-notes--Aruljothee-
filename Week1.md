## 1. GET Request

### Command

```bash
curl.exe -i https://api.github.com/users/torvalds
```

### Status Code

#### 200 OK

The request was successful.

### Response Headers

#### 1. Content-Type

application/json

This means the response data is in JSON format.

#### 2. Cache-Control

public, max-age=60

This gives information about caching the response.

#### 3. ETag

ETag is used to identify a version of the response.

### Response Body

The response body contains the details of the GitHub user torvalds.

Some fields I observed:

* login - GitHub username
* name - User name
* company - Company name
* location - User location
* public_repos - Number of public repositories
* followers - Number of followers

### Observation

I used a GET request to get the details of the GitHub user. The server returned 200 OK and the data was in JSON format.

---

## 2. Verbose GET Request

### Command

```bash
curl.exe -v https://httpbin.org/get
```

### Status Code

#### 200 OK

The request was successful.

### Response Headers

#### 1. Content-Type

application/json

The response is in JSON format.

#### 2. Content-Length

255

This shows the size of the response.

#### 3. Server

gunicorn/19.9.0

This shows the server software.

### Response Body

The response contains information about the request, such as the headers, origin, and URL.

### Observation

I used the verbose curl command to see more details about the request and response. It showed the connection, request, response, headers, and response body.

---

## 3. POST Request

### Command

```bash
curl.exe -i -X POST https://httpbin.org/post -H "Content-Type: application/json" -d "{\"name\":\"Arul\",\"week\":1}"
```

### Status Code

#### 200 OK

The request was successful.

### Response Headers

#### 1. Content-Type

application/json

The response is in JSON format.

#### 2. Content-Length

255

This shows the size of the response.

#### 3. Server

gunicorn/19.9.0

This shows the server software.

### Response Body

The response contains the data that I sent and other request information.

### Observation

I used a POST request to send JSON data to the server. The server received the data and returned a response.

---

## 4. Query Parameters

### Command

```bash
curl.exe -i "https://httpbin.org/get?role=intern&track=java"
```

### Status Code

#### 200 OK

The request was successful.

### Query Parameters

* role=intern - The role is intern.
* track=java - The learning track is Java.

### Response Body

The response contains the query parameters that I sent.

### Observation

I used query parameters to send extra information to the server through the URL. The server returned the values in JSON format.

---

## 5. Non-Existing User

### Command

```bash
curl.exe -i https://api.github.com/users/this-user-does-not-exist-99999
```

### Status Code

#### 404 Not Found

The requested user was not found.

### Response Body

The response contains an error message saying that the user was not found.

### Observation

I requested a GitHub user that does not exist. The server returned 404 Not Found because the requested user was not available.

---

## Postman Verification

### GET Request

I sent the same GET request using Postman. I received a successful response with 200 O`.

### POST Request

I sent the same POST request using Postman with the JSON data. The request was successful and I received a response from the server.

### Final Observation

I learned how to use GET and POST requests with curl. I also learned how to use query parameters, read response headers, understand status codes, and send JSON data.
