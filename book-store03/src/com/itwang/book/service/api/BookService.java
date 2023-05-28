package com.itwang.book.service.api;

import com.itwang.book.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getIndexBookList();

    List<Book> getBookList();

    Integer addBook(Book book);

    Book selectBookInfo(String id);

    Integer update(Book book);

    Integer deleteById(String id);

    Book getBookById(String bookId);
}
