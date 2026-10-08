# LAB 3 – COMMAND LINE CONTACT BOOK

## 1. Contact Book

- A Contact Book is a simple application used to store and manage contact details.
- It is a command-line application.
- The user interacts with the program through the terminal.
- Main operations:
  - Add contact
  - List contacts
  - Find contact
  - Delete contact
  - Quit

---

## 2. Contact Class

- Contact is a class used to represent one contact.
- It contains contact information such as:
  - Name
  - Phone number
- A class is a blueprint for creating objects.

Example:

```java
class Contact {
    String name;
    String phone;
}
```

- An object is created from the class.

```java
Contact c = new Contact("Arun", "9876543210");
```

---

## 3. Map<String, Contact>

- Map stores data in the form of **key-value pairs**.
- In this project:
  - Key → Contact name
  - Value → Contact object

Example:

```java
Map<String, Contact> contacts = new HashMap<>();
```

- String represents the name.
- Contact represents the contact object.

### Important Map methods

- put() → adds a contact.
- get() → gets a contact.
- remove() → deletes a contact.
- containsKey() → checks whether a contact exists.
- values() → gets all contact objects.

Example:

```java
contacts.put("Arun", contact);
```

---

## 4. HashMap

- HashMap is a class that implements the Map interface.
- It stores data as key-value pairs.
- It allows fast searching using keys.
- In our project, the contact name is used as the key.

Example:

```java
Map<String, Contact> contacts = new HashMap<>();
```

---

## 5. Menu Loop

- The menu should be displayed repeatedly.
- A loop is used to keep the menu running.
- while(true) can be used for the continuous loop.
- The loop stops when the user chooses **Quit**.
- break or return can be used to stop the loop.

Menu options:

1. Add Contact
2. List Contact
3. Find Contact
4. Delete Contact
5. Quit

---

## 6. Add Contact

- User enters the contact name.
- User enters the phone number.
- A Contact object is created.
- The object is stored in the Map.
- Empty names should not be accepted.

Example:

```java
contacts.put(name, new Contact(name, phone));
```

---

## 7. List Contacts

- Displays all contacts stored in the Map.
- A for loop can be used to display the contacts.
- contacts.values() returns all contact objects.
- If there are no contacts, display:

```text
No contacts found.
```

---

## 8. Find Contact

- User enters the contact name.
- get() is used to search for the contact.

```java
Contact contact = contacts.get(name);
```

- If the contact exists, display the name and phone number.
- If it does not exist, display:

```text
Contact not found.
```

---

## 9. Delete Contact

- User enters the contact name.
- Check whether the contact exists.
- remove() is used to delete the contact.

```java
contacts.remove(name);
```

- If the contact does not exist, display:

```text
Contact not found.
```

---

## 10. Exception Handling

- Exception handling is used to handle errors during program execution.
- Java uses:
  - try
  - catch
  - finally
  - throw
  - throws
- In this project, try-catch is mainly used for invalid input.

Example:

```java
try {
    int choice = scanner.nextInt();
} catch (InputMismatchException e) {
    System.out.println("Invalid input");
}
```

---

## 11. InputMismatchException

- InputMismatchException occurs when the entered input does not match the expected data type.
- Example:
  - Program expects an integer.
  - User enters text.

Example:

```java
int choice = scanner.nextInt();
```

If the user enters:

```text
abc
```

an InputMismatchException can occur.

- try-catch prevents the program from crashing.

---

## 12. Scanner

- Scanner is used to get input from the user.
- It is available in the java.util package.

Example:

```java
Scanner scanner = new Scanner(System.in);
```

- `nextInt()` → reads an integer.
- `nextLine()` → reads a complete line.
- `next()` → reads one word.

---

# Java Concepts

## 13. Java Static vs Dynamic Typing

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

## 14. Method Overloading

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

## 15. Method Overriding

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

## 16. Class

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

## 17. Object

- An object is an instance of a class.
- It contains the data and behavior defined by the class.
- A Contact object represents one contact.

Example:

```java
Contact contact = new Contact("Arun", "9876543210");
```

---

## 18. Constructor

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

## 19. Encapsulation

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

## 20. Interface

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

## 21. Compile-Time Polymorphism

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

## 22. Runtime Polymorphism

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

## 23. `break`

- break is used to stop a loop or switch statement.
- In the Contact Book, it can be used to exit the menu loop.

Example:

```java
if (choice == 5) {
    break;
}
```

---

## 24. `return`

- return is used to exit from a method.
- It can also be used to stop the main() method and end the program.

Example:

```java
if (choice == 5) {
    return;
}
```

---

## 25. `this` Keyword

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

## 26. `@Override` Annotation

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
