package com.itwang.book.service.impl;

import com.itwang.book.dao.model.api.BookDao;
import com.itwang.book.dao.model.impl.BookDaoImpl;
import com.itwang.book.entity.Book;
import com.itwang.book.service.api.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    /**
     * 获取主页图书列表
     * @return
     */
    @Override
    public List<Book> getIndexBookList() {
        return bookDao.getIndexBookList();
    }

    /**
     * 获取后台图书数据
     * @return
     */
    @Override
    public List<Book> getBookList() {
        return bookDao.getBookList();
    }

    @Override
    public Integer addBook(Book book) {
        return bookDao.addBook(book);
    }

    /**
     * 根据id获取修改的回显数据
     * @param id
     * @return
     */
    @Override
    public Book selectBookInfo(String id) {
        return bookDao.selectBookInfo(id);
    }

    /**
     * 根据id修改
     * @param book
     * @return
     */
    @Override
    public Integer update(Book book) {
        return bookDao.update(book);
    }

    /**
     * 根据id删除 一本书
     * @param id
     * @return
     */
    @Override
    public Integer deleteById(String id) {
        return bookDao.delete(id);
    }

    /**
     * 根据bookId 查询 book实体
     * @param bookId
     * @return
     */
    @Override
    public Book getBookById(String bookId) {
        return bookDao.getBookById(bookId);
    }
}
