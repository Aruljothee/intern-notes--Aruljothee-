# Java Concepts

## 1. Java Static vs Dynamic Typing

- Java is a **statically typed language**.
- The data type of a variable is declared before using it.
- The compiler checks the types during compilation.

Example:

```java
int age = 20;
```

This is invalid:

```java
age = "hello";
```

because age is an integer.

### What does the compiler catch?

- Type mismatch
- Invalid assignments
- Many syntax errors
- Many compile-time errors

---

## 2. Method Overloading

- Overloading means having multiple methods with the same name.
- The methods must have different parameters.
- It usually occurs within the same class.
- It is an example of compile-time polymorphism.

Example:

```java
void add(int a, int b) {
}

void add(int a, int b, int c) {
}
```

- Both methods have the same name but different parameters.

---

## 3. Method Overriding

- Overriding occurs between a parent class and child class.
- The child class provides its own implementation of a parent method.
- The method name and parameters remain the same.
- @Override annotation is commonly used.
- It is related to runtime polymorphism.

Example:

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
```

---

## 4. Class

- A class is a blueprint for creating objects.
- It defines the properties and methods of an object.
- In this project, Contact is a class.

Example:

```java
class Contact {
    String name;
    String phone;
}
```

---

## 5. Object

- An object is an instance of a class.
- It contains the data and behavior defined by the class.
- A Contact object represents one contact.

Example:

```java
Contact contact = new Contact("Arun", "9876543210");
```

---

## 6. Constructor

- A constructor is used to initialize an object.
- It has the same name as the class.
- It does not have a return type.
- It is automatically called when an object is created.

Example:

```java
class Contact {

    String name;
    String phone;

    Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
```

---

## 7. Encapsulation

- Encapsulation means wrapping data and methods together inside a class.
- It helps protect the data from direct access.
- private variables and public getter/setter methods are commonly used.

Example:

```java
class Contact {

    private String name;
    private String phone;

    public String getName() {
        return name;
    }
}
```

---

## 8. Interface

- An interface is used to define a set of methods that a class can implement.
- It provides a contract for classes.
- `Map` is an interface in Java.
- `HashMap` is a class that implements the `Map` interface.

Example:

```java
Map<String, Contact> contacts = new HashMap<>();
```

- Here:
  - Map → interface
  - HashMap → implementation

---

## 9. Compile-Time Polymorphism

- Compile-time polymorphism is achieved through method overloading.
- The compiler decides which overloaded method should be called.
- The method name is the same, but the parameters are different.

Example:

```java
void display(int number) {
}

void display(String text) {
}
```

---

## 10. Runtime Polymorphism

- Runtime polymorphism is achieved through method overriding.
- The method that is executed is decided at runtime.
- It occurs between parent and child classes.

Example:

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
```

---

## 11. `break`

- break is used to stop a loop or switch statement.
- In the Contact Book, it can be used to exit the menu loop.

Example:

```java
if (choice == 5) {
    break;
}
```

---

## 12. `return`

- return is used to exit from a method.
- It can also be used to stop the main() method and end the program.

Example:

```java
if (choice == 5) {
    return;
}
```

---

## 13. `this` Keyword

- this refers to the current object.
- It is commonly used when constructor parameters and instance variables have the same name.

Example:

```java
class Contact {

    String name;

    Contact(String name) {
        this.name = name;
    }
}
```

- this.name refers to the object's instance variable.
- name refers to the constructor parameter.

---

## 14. `@Override` Annotation

- @Override is used when a child class overrides a parent class method.
- It tells the compiler that the method is intended to override a parent method.
- It helps detect mistakes in method overriding.

Example:
```java
@Override
void sound() {
    System.out.println("Dog barks");
}
```
