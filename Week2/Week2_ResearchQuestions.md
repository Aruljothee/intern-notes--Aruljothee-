# Research Deliverable – Set 2

## Fundamentals, Revisited Deeper

### 1. Front End vs Back End

**Front End** is the part of a web application that the user can see and interact with. It includes buttons, forms, menus, web pages, and images. Common front-end technologies are HTML, CSS, JavaScript, React, and Angular.

**Back End** is the part of the application that works behind the scenes. It handles business logic, databases, authentication, and requests from the front end. Common back-end technologies include Java with Spring Boot, Python, Node.js, and PHP.

For example, when a user clicks the Login button, the front end collects the username and password and sends them to the back end. The back end checks the details in the database and sends the result back to the front end.

---

### 2. Three-Tier Architecture in a Web Application

Three-tier architecture divides a web application into three main layers. These are the **Presentation Layer, Business Logic Layer, and Data Layer**.

The **Presentation Layer** is responsible for the user interface. Technologies such as HTML, CSS, JavaScript, and React can be used here.

The **Business Logic Layer** contains the main logic of the application. For example, it can check login details, calculate prices, or apply business rules. Spring Boot can be used for this layer.

The **Data Layer** is responsible for communicating with the database. It can use databases such as MySQL, PostgreSQL, or MongoDB.

---

### 3. Three-Tier Architecture from a Web-Development Point of View

From a web-development point of view, the presentation tier is responsible for displaying the application to the user. For example, React can be used to create the user interface.

The business logic tier processes the user's request and performs the required operations. For example, Spring Boot can handle the business logic.

The data tier communicates with the database. For example, MySQL can store application data.

The user enters registration details in the front end. The request goes to the Spring Boot application. The service processes the information, and the repository stores the data in MySQL.

---

### 4. SSL / TLS Encryption

**SSL** and **TLS** are security technologies used to protect data sent between a client and a server.

SSL stands for **Secure Sockets Layer**, while TLS stands for **Transport Layer Security**. TLS is the modern and more secure technology, while SSL is an older technology.

TLS provides encryption, authentication, and data integrity. Encryption prevents other people from easily reading the data while it is being transmitted. Authentication helps verify the identity of the server. Integrity helps ensure that the data was not changed during transmission.

the communication between the browser and server is protected using TLS.

---

### 5. HTTP Methods

HTTP methods tell the server what type of operation the client wants to perform.

The **GET** method is used to retrieve or read data. For example, `GET /users` can be used to get a list of users.

The **POST** method is used to create new data. For example, `POST /users` can be used to create a new user.

The **PUT** method is commonly used to update an existing resource. For example, `PUT /users/10` can be used to update user number 10.

The **PATCH** method is used to partially update an existing resource. For example, it can be used to update only the email address of a user.

The **DELETE** method is used to delete data. For example, `DELETE /users/10` can be used to delete user number 10.

---

### 6. HTTP Status Codes

HTTP status codes tell the client what happened after sending a request to the server.

Status codes beginning with **1** are informational responses. They provide information about the request.

Status codes beginning with **2** indicate success. For example, **200 OK** means the request was successful, and **201 Created** means a new resource was successfully created.

Status codes beginning with **3** indicate redirection. They tell the client that it may need to take another action to complete the request.

Status codes beginning with **4** indicate a client-side error. For example, **400 Bad Request** means the request contains invalid information. **401 Unauthorized** means authentication is required or has failed. **403 Forbidden** means the user is authenticated but does not have permission to access the resource. **404 Not Found** means the requested resource does not exist.

Status codes beginning with **5** indicate a server-side error. For example, **500 Internal Server Error** means something went wrong on the server.

---

### 7. CRUD Operations

CRUD stands for **Create, Read, Update, and Delete**. These are the four basic operations performed on data.

**Create** means adding new data. In a REST API, POST is commonly used for this operation.

**Read** means retrieving existing data. GET is commonly used for reading data.

**Update** means changing existing data. PUT or PATCH can be used for this operation.

**Delete** means removing existing data. DELETE is commonly used for this operation.

---

### 8. Stateful vs Stateless Communication in Web Applications

**Stateful communication** means that the server remembers information about the client between requests. A common example is session-based authentication. After a user logs in, the server stores session information and uses it for later requests.

**Stateless communication** means that the server does not need to remember previous requests. Each request contains the information needed to process it. Token-based authentication is commonly used with stateless communication.

For example, in a stateless application, a client can send an access token with every request. The server checks the token and processes the request without depending on a stored session.

The main difference is that stateful communication maintains client information on the server, while stateless communication treats each request independently.

---

# Authentication & Authorization

## 1. What is Authentication? What are the Different Types?

**Authentication** means verifying the identity of a user.

There are different types of authentication.

**Username and password authentication** requires the user to provide a username and password. The server verifies these credentials.

**OTP authentication** uses a one-time password that is usually sent to a user's phone number or email address.

**Biometric authentication** uses physical characteristics such as a fingerprint, face, or iris to verify the user.

**Token-based authentication** gives the user a token after successful login. The client sends the token with later requests to prove its identity.

**OAuth or social login** allows users to authenticate using another service such as Google, GitHub, or Microsoft.

---

## 2. What is Authorization? What are the Different Types?

**Authorization** means checking what an authenticated user is allowed to access or perform.

One common type is **Role-Based Access Control (RBAC)**. In this method, permissions are based on the user's role. For example, an ADMIN can manage users while a USER can access only normal user features.

Another type is **Permission-Based Access Control**. In this method, users are given specific permissions such as `READ_USERS`, `CREATE_USERS`, or `DELETE_USERS`.

Another type is **Attribute-Based Access Control (ABAC)**. In this method, access is decided using attributes such as the user's role, department, location, or the type of resource being accessed.

---

## 3. How Does Authentication Differ from Authorization?

Authentication checks the identity of a user, while authorization checks what that user is allowed to do.

For example, when a user enters a username and password, authentication checks whether the user is really that person.

After the user successfully logs in, authorization checks whether that user can access a particular page or perform a particular operation.

Authentication normally happens before authorization.

---

# Serialization & Data Formats

## 1. What is Serialization? What is Deserialization? Why Do APIs Need Them?

**Serialization** means converting an object or data structure into a format that can be easily stored or transmitted.

**Deserialization** is the opposite process. It converts received data back into an object that the application can use.

APIs need serialization and deserialization because different applications need to exchange data. For example, a React application and a Spring Boot application can exchange information using JSON.

---

## 2. Name Some Serialization Formats

Some common serialization and data formats are **JSON, XML, YAML, CSV, and Protocol Buffers**.

JSON is one of the most commonly used formats in modern REST APIs because it is simple, lightweight, and easy for applications to process.

---

## 3. JSON and XML

**JSON** stands for **JavaScript Object Notation**. It represents data using key-value pairs.

For example:

json:
{
  "name": "Arul",
  "age": 20,
  "city": "Chennai"
}

JSON is commonly used in REST APIs. A server can return JSON in an HTTP response with a content type such as `application/json`.

**XML** stands for **Extensible Markup Language**. It represents data using tags.

For example:

xml
<user>
    <name>Arul</name>
    <age>20</age>
    <city>Chennai</city>
</user>


XML can also be used to exchange data between applications.

The main difference is that JSON mainly uses key-value pairs, while XML uses tags. JSON is usually shorter and is very common in modern REST APIs. XML can be more verbose and is still used in some enterprise and older systems.

---

# Tools & HTTP Concepts

## 1. Postman: What It Is and How You Test an API

**Postman** is a tool used to develop and test APIs. It allows developers to send HTTP requests without creating a front-end application.

Using Postman, we can send GET, POST, PUT, PATCH, and DELETE requests.

To test an API using Postman, first open Postman. Then select the required HTTP method and enter the API URL. If required, add request headers and a request body. Then click the **Send** button. Postman displays the server's response, status code, response headers, and response body.

The server processes this request and returns a response.

---

## 2. cURL: What It Is and How You Send an HTTP Request

**cURL** is a command-line tool used to communicate with servers and APIs.

It allows developers to send HTTP requests directly from a terminal or command prompt.

---

## 3. Postman vs cURL – When You Would Use Each

Postman is a graphical tool, so it is easier to use when manually testing APIs and viewing requests and responses. It provides an interface for selecting methods, adding headers, entering request bodies, and viewing responses.

cURL is a command-line tool. It is useful when working in a terminal, writing scripts, or automating HTTP requests.

---

## 4. Query Parameter, Path Variable, and Request Payload

A **query parameter** is additional information added to the URL after a question mark (`?`).

For example:

text:
GET /users?name=arul

Here, `name=arul` is a query parameter.

Query parameters are commonly used for searching, filtering, sorting, and pagination.

A **path variable** is a value included directly in the URL path.

For example:

text:
GET /users/10

Here, `10` is the path variable. It can identify a specific user.

In Spring Boot, it can be written as:

java:
@GetMapping("/users/{id}")

The `{id}` represents the path variable.

The main difference is that query parameters are generally used to provide optional information such as filtering or searching, while path variables are commonly used to identify a particular resource.

For example:

text:
/users/10

means to access user number 10.

text:
/users?name=arul

means to find users based on the name Arul.

A **request payload** is the data sent by the client to the server in the request body.

For example:

json:
{
  "username": "arul",
  "password": "1234"
}


This data can be sent when creating or updating a user.

In simple words:

> Query parameter = Extra information in the URL.

> Path variable = Resource identifier in the URL.

> Request payload = Data sent in the request body.

---

## 5. Request Headers and Response Headers

**Request headers** are pieces of information sent from the client to the server along with an HTTP request.

For example:

text:
Content-Type: application/json
Authorization: Bearer token
Accept: application/json

The `Content-Type` header tells the server what type of data is being sent. The `Authorization` header can contain authentication information. The `Accept` header tells the server what type of response the client can accept.

**Response headers** are pieces of information sent from the server back to the client along with the HTTP response.

For example:

text:
Content-Type: application/json
Cache-Control: no-cache

The response headers provide information about the response, such as the type of data being returned and caching instructions.
