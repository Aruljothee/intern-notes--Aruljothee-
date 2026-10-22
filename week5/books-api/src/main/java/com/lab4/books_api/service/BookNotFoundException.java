package com.lab4.books_api.service;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Book not found with ID: " + id);
    }
}