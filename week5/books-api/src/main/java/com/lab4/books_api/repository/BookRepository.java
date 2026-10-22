package com.lab4.books_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab4.books_api.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}