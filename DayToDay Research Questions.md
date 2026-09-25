
TECHNICAL RESEARCH NOTES

1. Hibernate and ORM in Spring Boot

1.1 Are you using Hibernate in your Spring Boot application?

Yes, I am using Hibernate as the ORM (Object-Relational Mapping) framework in my Spring Boot application through Spring Data JPA.

1.2 If not, which ORM are you using?

Hibernate is the ORM framework used in my application.

JPA (Java Persistence API) is a Java specification that defines how Java objects interact with databases.

Hibernate is an ORM framework that implements the JPA specification.

1.3 Where are you mentioning ORM details in your Spring Boot application?

ORM-related details are mentioned in the pom.xml file, entity classes, and application.properties file.

The pom.xml file contains the Spring Data JPA dependency, which uses Hibernate by default.

Entity classes use annotations such as @Entity, @Table, and @Id to define how Java classes are mapped to database tables.

The application.properties file is used to configure the database connection and JPA/Hibernate settings.


2. What Is Swagger or OpenAPI?

OpenAPI is a standard used to describe REST APIs, including their endpoints, parameters, requests, and responses.

Swagger is a set of tools that helps developers create, view, and test API documentation. Swagger UI provides an interactive interface for viewing and testing APIs.

2.1 What Is It Used For?

Swagger and OpenAPI are used to document REST APIs, understand API endpoints, test APIs from a browser, and view request and response formats.

Simple explanation:

Swagger UI helps developers view and test API endpoints without manually writing every request in Postman.


3. What Is Boilerplate Code?

3.1 Definition

Boilerplate code is repetitive code that developers write in many applications to perform common tasks.

3.2 How Is Boilerplate Code Related to Frameworks and Libraries?

Frameworks and libraries help reduce repetitive coding by providing ready-made features.

Spring Boot provides application configuration and setup features, reducing the amount of manual configuration required.

Lombok generates common methods such as getters, setters, and constructors, reducing repetitive Java code.

Spring Data JPA provides common database operations through repository interfaces, reducing the need to write basic database queries manually.

React provides reusable components for creating user interfaces, which helps reduce repetitive UI development.

Simple explanation:

Frameworks and libraries provide ready-made features, so developers do not have to write the same code repeatedly.


4. Separation of Duties / Separation of Concerns

Separation of Concerns (SoC) means dividing an application into different parts, where each part has a specific responsibility.

Separation of Duties is a related security and organizational concept that divides responsibilities among people or components to reduce misuse and errors.

4.1 How Is Separation of Concerns Achieved in Java Spring Boot?

Spring Boot applications commonly separate responsibilities into different layers.

The Controller layer handles HTTP requests and responses.

The Service layer contains business logic.

The Repository layer communicates with the database.

The Entity layer represents database data.

The Security layer handles authentication and authorization configuration.

Simple explanation:

Each layer performs a specific task, making the application easier to understand, test, and maintain.

Application Flow:

Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Database


5. What Is the SOLID Principle?

SOLID is a group of five principles used to create maintainable and flexible object-oriented software.

S – Single Responsibility Principle: A class should have one main responsibility.

O – Open/Closed Principle: Software should be open for extension but closed for unnecessary modification.

L – Liskov Substitution Principle: A child class should work correctly wherever its parent type is expected.

I – Interface Segregation Principle: Developers should prefer smaller, specific interfaces instead of one large interface.

D – Dependency Inversion Principle: High-level code should depend on abstractions rather than concrete implementations.

Simple explanation:

SOLID principles help developers write clean, flexible, and maintainable code.


6. What Design Pattern Is Used Here?

Code:

return ResponseEntity.notFound().build();

6.1 Explanation

This code returns an HTTP 404 Not Found response without a response body.

ResponseEntity.notFound() creates a builder for a 404 response.

The .build() method builds the final ResponseEntity.

6.2 Is It a Design Pattern?

This code is not, by itself, a complete implementation of a formal design pattern such as Singleton or Factory Method.

It uses a fluent API and a factory-style method to construct the response.

Simple explanation for your boss:

This code creates and returns a 404 Not Found HTTP response when the requested resource does not exist.


7. Demo – Calling APIs Using Postman and cURL

7.1 What Is Postman?

Postman is a tool used to send HTTP requests and test APIs.

We can use Postman to test different HTTP methods, such as GET, POST, PUT, and DELETE.

GET: The GET method is used to retrieve data from a server.

POST: The POST method is commonly used to create a new resource on the server.

PUT: The PUT method is used to replace an existing resource completely or create it if the server supports that behavior.

DELETE: The DELETE method is used to request the removal of a resource from the server.

7.2 What Is cURL?

cURL is a command-line tool used to transfer data over network protocols such as HTTP.

It can be used to send API requests and view the responses directly from a terminal.

7.3 What Are Headers?

Headers contain additional information about an HTTP request or response.

For example, the Content-Type: application/json header tells the server that the request body contains JSON data.

7.4 What Is a Payload?

A payload is the actual data sent in the body of an HTTP request.

For example, a registration request may contain a JSON payload with a username and password.

7.5 What Is Verbose Mode in cURL?

Verbose mode displays additional details about the request and response.

When we execute cURL in verbose mode, we can observe DNS resolution or hostname processing, TCP connection details, request headers, response headers, the HTTP status code, and the response body.

Simple explanation:

cURL verbose mode helps us understand how the client communicates with the server at the HTTP and network level.

Important:

Verbose output may expose sensitive request headers or credentials. Avoid sharing it publicly without removing secrets.


8. Why Are Passwords Sent in Headers or Payloads and Not in the URL?

Passwords should not be included in URLs or query parameters because URLs may be recorded in browser history, server logs, proxies, and monitoring systems.

Passwords are commonly sent in the request body for login or registration requests.

Passwords or authentication credentials may also be sent through appropriate authorization headers, such as Basic Authentication or Bearer token authentication.

Simple explanation:

Passwords are generally sent in the request body or appropriate authentication headers instead of the URL to reduce accidental exposure through logs and browser history.

8.1 Important Security Points

We should always use HTTPS to encrypt data in transit.

HTTP Basic Authentication encodes credentials using Base64, but Base64 is not encryption.

Passwords should be hashed securely before storing them in a database.


9. What Are Annotations in Java and Decorators in Python?

9.1 Java Annotations

Annotations provide metadata or instructions to Java tools, frameworks, and the compiler.

Annotations use the @ symbol to provide additional information about a class, method, or field.

9.2 Common Spring Boot Annotations

The @RestController annotation marks a class as a REST controller.

The @GetMapping annotation maps an HTTP GET request to a method.

The @PostMapping annotation maps an HTTP POST request to a method.

The @Service annotation marks a service component.

The @Repository annotation marks a repository component.

The @Entity annotation marks a class as a JPA entity.

The @Autowired annotation requests dependency injection.

The @Configuration annotation marks a configuration class.

Simple explanation:

Annotations help Spring and other Java tools understand how classes, methods, and fields should be handled.

9.3 Python Decorators

A decorator is a Python feature that modifies or extends the behavior of a function or class.

A decorator adds functionality to a function without changing the function's main code.

9.4 Common FastAPI Example

from fastapi import APIRouter

router = APIRouter()

@router.get("/users")
def get_users():
    return {"message": "List of users"}

9.5 What Is @router.get() in FastAPI?

@router.get("/users") is a decorator method that registers the function as a GET API endpoint.

Simple explanation:

@router.get("/users") tells FastAPI to call the get_users() function when a GET request is sent to /users on that router.


10. What Is Lombok in Java?

Lombok is a Java library that reduces repetitive code by generating common methods during compilation.

The @Getter annotation generates getter methods.

The @Setter annotation generates setter methods.

The @Data annotation generates getters, setters, and other methods such as toString(), equals(), and hashCode().

The @NoArgsConstructor annotation generates a no-argument constructor.

The @AllArgsConstructor annotation generates a constructor with all fields.

The @RequiredArgsConstructor annotation generates a constructor for required fields, such as final fields.

Simple explanation:

Lombok helps reduce boilerplate code by automatically generating common Java methods.

Important:

@Data also generates toString(), which can expose sensitive fields such as passwords if you log the object. Avoid using it carelessly for credential-containing entities.


11. What Is Dependency Injection?

Dependency Injection (DI) is a technique where an object receives the objects it depends on from an external source instead of creating them itself.

11.1 Why Is It Used?

Dependency Injection reduces tight coupling, makes code easier to test, improves maintainability, and allows frameworks to manage object creation.

11.2 Example in Spring Boot

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

Simple explanation:

Spring creates the UserRepository object and provides it to the UserService through the constructor.

11.3 How It Works

Spring Container
      ↓
Creates UserRepository
      ↓
Injects into UserService

Simple explanation for your boss:

Dependency Injection allows Spring Boot to provide required objects automatically, so we do not need to manually create them using new everywhere.


12. What Is 127.0.0.1?

127.0.0.1 is an IPv4 loopback address that refers to the local computer.

12.1 Is It a Special IP Address?

Yes. It belongs to the IPv4 loopback address range, which is 127.0.0.0/8.

The most commonly used loopback address is 127.0.0.1.

12.2 Example

http://127.0.0.1:8080

Simple explanation:

127.0.0.1 points back to the same computer where the request is running.

12.3 Example in Spring Boot

When you run your application locally on port 8080, you can access it using:

http://localhost:8080

localhost normally resolves to a local loopback address, often 127.0.0.1 for IPv4.


13. Security and Encryption

13.1 What Is a Symmetric Key?

Symmetric encryption uses the same secret key for encryption and decryption.

Plaintext + Secret Key
        ↓
     Encryption
        ↓
     Ciphertext
        ↓
     Decryption
        ↓
Plaintext + Same Secret Key

Simple explanation:

Symmetric encryption uses one shared secret key to encrypt and decrypt data.

Example: AES.

13.2 What Is a Public Key?

A public key is a key that can be shared with others in a public-key cryptosystem.

It can be used for encryption or signature verification, depending on the algorithm and operation.

Simple explanation:

A public key can be shared publicly and is used in asymmetric cryptography for operations such as encryption or signature verification.

13.3 What Is a Private Key?

A private key is a secret key that must be protected by its owner.

It can be used for decryption or creating digital signatures, depending on the cryptographic algorithm.

Simple explanation:

A private key must be kept secret and is used for operations such as decryption or signing.

13.4 What Is Asymmetric Encryption?

Asymmetric cryptography uses a pair of related keys.

The two keys are called the public key and the private key.

Simple explanation:

Asymmetric cryptography uses two related keys instead of one shared secret key.

Examples include RSA and elliptic-curve cryptography (ECC).

13.5 Symmetric vs Asymmetric Encryption

Symmetric encryption uses one shared secret key, and it is generally efficient for bulk data encryption. AES is an example of symmetric encryption.

Asymmetric cryptography uses a public and private key pair. It is useful for key exchange and digital signatures. RSA and ECC are examples of public-key cryptography.


14. What Is a Digital Certificate?

A digital certificate is an electronic document that binds an identity to a public key.

It is commonly used in HTTPS to help a browser verify the identity of a server.

14.1 What Does a Certificate Contain?

A digital certificate may contain subject or identity information, a public key, issuer information, a validity period, and a digital signature from the certificate issuer.

Simple explanation:

A digital certificate helps a browser verify that a public key belongs to the identity mentioned in the certificate.


15. What Is a Certificate Authority (CA)?

A Certificate Authority (CA) is an organization or trusted entity that issues and signs digital certificates.

15.1 What Is Its Role?

A Certificate Authority validates identity according to the certificate type and issuance requirements.

It issues digital certificates and digitally signs them.

It also supports certificate trust and lifecycle processes.

Simple explanation:

A Certificate Authority issues digital certificates and helps browsers verify trusted identities.

15.2 Examples

Let's Encrypt is a Certificate Authority.

DigiCert is a Certificate Authority.

Sectigo is a Certificate Authority.

Certificate trust depends on the CA and trust chain recognized by the client. A certificate does not automatically guarantee that a website is safe in every respect.


16. What Is Base64 Encoding and Decoding?

16.1 Base64 Encoding

Base64 is a method of converting binary data into text using a defined set of characters.

Simple explanation:

Base64 encoding converts data into a text representation that is easier to transmit in systems designed for text.

16.2 Base64 Decoding

Decoding converts Base64 text back into the original bytes.

Simple explanation:

Base64 decoding converts Base64-encoded data back into its original byte representation.

16.3 Where Is Base64 Used?

Base64 is used for encoding binary data in JSON or text-based formats.

It is also used for email attachments, data URLs, and HTTP Basic Authentication credentials.

16.4 Example

Original text: Hello
Base64:        SGVsbG8=

16.5 Important Security Note

Base64 is encoding, not encryption. Anyone who has the encoded data can decode it.

For example, HTTP Basic Authentication uses Base64 encoding for credentials, so HTTPS is essential to protect them in transit.
```
