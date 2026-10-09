# COMMAND LINE CONTACT BOOK

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

