package com.example.goodreads.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.goodreads.model.Book;
import com.example.goodreads.repository.BookJpaRepository;
import com.example.goodreads.repository.BookRepository;

@Service
public class BookJPAService implements BookRepository {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Override
    public ArrayList<Book> getBooks() {
        List<Book> bookList = bookJpaRepository.findAll(); 
        ArrayList<Book> books = new ArrayList<>(bookList);
        return books;
    }

    @Override
    public Book getBookById(int bookId) {
       try {
        Book book = bookJpaRepository.findById(bookId).get();
        return book;
       } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
    }

    @Override
    public Book addBook(Book book) {
        return bookJpaRepository.save(book);
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        try {
            Book newBook = bookJpaRepository.findById(bookId).get();
            if (book.getName()!= null) {
                newBook.setName(book.getName());
            }
            if (book.getImageUrl() != null) {
                newBook.setImageUrl(book.getImageUrl());
            }
            bookJpaRepository.save(newBook);

            return newBook;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteBook(int bookId) {
        try {
            bookJpaRepository.deleteById(bookId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    
}
