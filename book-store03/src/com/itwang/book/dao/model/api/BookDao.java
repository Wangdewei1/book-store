package com.itwang.book.dao.model.api;

import com.itwang.book.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> getIndexBookList();

    List<Book> getBookList();

    Integer addBook(Book book);

    Book selectBookInfo(String id);

    Integer update(Book book);

    Integer delete(String id);

    Book getBookById(String bookId);

}
