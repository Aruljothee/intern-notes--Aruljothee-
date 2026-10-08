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

- `Contact` is a class used to represent one contact.
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

- `Map` stores data in the form of **key-value pairs**.
- In this project:
  - Key → Contact name
  - Value → Contact object

Example:

```java
Map<String, Contact> contacts = new HashMap<>();
```

- `String` represents the name.
- `Contact` represents the contact object.

### Important Map methods

- `put()` → adds a contact.
- `get()` → gets a contact.
- `remove()` → deletes a contact.
- `containsKey()` → checks whether a contact exists.
- `values()` → gets all contact objects.

Example:

```java
contacts.put("Arun", contact);
```

---

## 4. HashMap

- `HashMap` is a class that implements the `Map` interface.
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
- `while(true)` can be used for the continuous loop.
- The loop stops when the user chooses **Quit**.
- `break` or `return` can be used to stop the loop.

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
- A `Contact` object is created.
- The object is stored in the `Map`.
- Empty names should not be accepted.

Example:

```java
contacts.put(name, new Contact(name, phone));
```

---

## 7. List Contacts

- Displays all contacts stored in the `Map`.
- `for` loop can be used to display the contacts.
- `contacts.values()` returns all contact objects.
- If there are no contacts, display:

```text
No contacts found.
```

---

## 8. Find Contact

- User enters the contact name.
- `get()` is used to search the contact.

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
- `remove()` is used to delete the contact.

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
  - `try`
  - `catch`
  - `finally`
  - `throw`
  - `throws`

- In this project, `try-catch` is mainly used for invalid input.

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

- `InputMismatchException` occurs when the entered input does not match the expected data type.
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

an `InputMismatchException` can occur.

- `try-catch` prevents the program from crashing.

---

## 12. Scanner

- `Scanner` is used to get input from the user.
- It is available in the `java.util` package.

Example:

```java
Scanner scanner = new Scanner(System.in);
```

- `nextInt()` → reads an integer.
- `nextLine()` → reads a complete line.
- `next()` → reads one word.

---

# Stand-up Questions

## 13. Difference between `/`, `//` and `%`

### `/`

- Used for division.
- Example:

```text
10 / 3 = 3.33
```

### `//`

- In Python, `//` is used for floor division.
- Example:

```text
10 // 3 = 3
```

- In Java, `//` is used to write a single-line comment.

### `%`

- Used to find the remainder.
- Example:

```text
10 % 3 = 1
```

---

## 14. Why does `range(1, 6)` stop at 5?

- `range()` is a Python function.
- It includes the starting value.
- It excludes the ending value.

```python
range(1, 6)
```

gives:

```text
1, 2, 3, 4, 5
```

- 6 is not included.

---

## 15. Tuple vs List

### List

- List is ordered.
- List is mutable.
- Mutable means it can be changed.
- Lists use square brackets `[ ]`.
- Duplicate values are allowed.

Example:

```python
numbers = [1, 2, 3]
```

### Tuple

- Tuple is ordered.
- Tuple is immutable.
- Immutable means it cannot normally be changed.
- Tuples use parentheses `( )`.
- Useful for fixed data.

Example:

```python
numbers = (1, 2, 3)
```

---

## 16. Set vs List

### List

- Maintains order.
- Allows duplicate values.
- Can be changed.

Example:

```python
[1, 2, 2, 3]
```

### Set

- Stores unique values.
- Does not allow duplicate values.
- Useful for membership checking.

Example:

```python
{1, 2, 3}
```

---

## 17. What does `input()` always return?

- `input()` is a Python function.
- It always returns a **string**.
- Even if the user enters a number, it is initially stored as a string.

Example:

```python
age = input("Enter age: ")
```

If the user enters `20`, the value is:

```text
"20"
```

- To convert it to an integer:

```python
age = int(input("Enter age: "))
```

---

## 18. What is a `venv`?

- `venv` means **Virtual Environment**.
- It is used in Python.
- It creates an isolated environment for a project.
- It keeps project dependencies separate.
- Different projects can use different package versions.

---

## 19. Why does each project get its own venv?

- Different projects may require different package versions.
- Installing everything globally can cause conflicts.
- A separate venv keeps each project's dependencies independent.

---

## 20. What does `if __name__ == "__main__":` do?

- This is used in Python.
- It checks whether the Python file is being run directly.
- If it is run directly, `main()` will execute.
- If the file is imported into another file, `main()` will not automatically execute.

Example:

```python
if __name__ == "__main__":
    main()
```

---

# Java Concepts

## 21. Java Static vs Dynamic Typing

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

because `age` is an integer.

### What does the compiler catch?

- Type mismatch
- Invalid assignments
- Many syntax errors
- Many compile-time errors

---

## 22. Method Overloading

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

## 23. Method Overriding

- Overriding occurs between a parent class and child class.
- The child class provides its own implementation of a parent method.
- The method name and parameters remain the same.
- `@Override` annotation is commonly used.
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
