# Week 1 - Lab 1

## 1. GET Request

### Command

```bash
curl.exe -i https://api.github.com/users/torvalds
```

### Status Code

#### 200 OK

The request was successful and the GitHub user details were received.

### Response Headers

#### 1. Content-Type

`application/json`

This means the response is in JSON format.

#### 2. Cache-Control

`public, max-age=60`

This tells us how the response can be cached.

#### 3. ETag

ETag is used to identify the version of the response.

### Response Body

The response body contains the details of the GitHub user `torvalds`.

Some of the fields I observed are:

- `login` - GitHub username
- `name` - User name
- `company` - Company name
- `location` - User location
- `public_repos` - Number of public repositories
- `followers` - Number of followers

### Observation

I used the GET request to get the GitHub user details. The request was successful and returned `200 OK`. The response data was in JSON format.

## 2. Verbose GET Request

### Command

```bash
curl.exe -v https://httpbin.org/get
```
### Status Code

#### 200 OK

The request was successful and the server returned a response.

### Response Headers

1. Content-Type: application/json

   - The response is in JSON format.

2. Content-Length: 255

   - Shows the size of the response body.

3. Server: gunicorn/19.9.0

   - Shows the server software handling the request.

### Response Body

The response body contains information about the request, including the request headers, origin, and URL.

### Observation

I used the verbose curl command to view the complete request and response details. It showed the connection, HTTP request, response status, headers, and response body.

## 3. POST Request

### Command

```bash
curl.exe -i -X POST https://httpbin.org/post -H "Content-Type: application/json" -d "{\"name\":\"Arul\",\"week\":1}"
```
### Status Code

**200 OK**

The request was successful and the server returned a response.

### Response Headers

1. **Content-Type:** application/json
   - The response is in JSON format.

2. **Content-Length:** 255
   - Shows the size of the response body.

3. **Server:** gunicorn/19.9.0
   - Shows the server software handling the request.

### Response Body

The response body contains information about the request, including the request headers, origin, and URL.

### Observation

I used the verbose curl command to view the complete request and response details. It showed the connection, HTTP request, response status, headers, and response body.

## 4. Query Parameters

### Command

```bash
curl.exe -i "https://httpbin.org/get?role=intern&track=python"
```
Status Code

200 OK

The request was successful and the server returned a response.

Query Parameters
role=intern - Specifies the role as intern.
track=python - Specifies the learning track as Python.
Response Body

The response body contains the query parameters sent with the GET request.

Observation

I used query parameters in the URL to send additional information to the server. The response showed the values of role and track in JSON format.
