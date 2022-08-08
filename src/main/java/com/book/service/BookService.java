package com.book.service;

import com.book.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    List<Book> getActiveBookList();

    Map<Book, Boolean> getBookList();

    void deleteBook(int bid);

    void addBook(String title, String desc, double price);

}
