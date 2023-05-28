package com.itwang.book.dao.model.impl;

import com.itwang.book.dao.base.impl.BaseDaoImpl;
import com.itwang.book.dao.model.api.BookDao;
import com.itwang.book.entity.Book;

import java.util.List;

public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {
    /**
     * 查询 t_book表中的数据 显示主页信息
     * @return
     */
    @Override
    public List<Book> getIndexBookList() {
        String sql = "select book_id bookId , book_name bookName,author,price,sales,stock,img_path imgPath from t_book";
        return selectAllData(sql, Book.class);
    }

    /**
     * 查询后台 t_book表中的数据 到后台管理页面
     * @return
     */
    @Override
    public List<Book> getBookList() {
        String sql = "select book_id bookId , book_name bookName,author,price,sales,stock,img_path imgPath from t_book";
        return selectAllData(sql, Book.class);
    }

    /**
     * 添加一本书，到后台管理
     * @param book
     * @return
     */
    @Override
    public Integer addBook(Book book) {
        String sql = "insert into t_book(book_name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql, book.getBookName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    /**
     * 根据查询 图书数据  返回给修改的回显
     * @param id
     * @return
     */
    @Override
    public Book selectBookInfo(String id) {
        String sql = "select book_id bookId , book_name bookName,author,price,sales,stock,img_path imgPath from t_book where book_id = ?";
        return selectOne(sql, Book.class, id);
    }

    /**
     * 根据id修改 图书管理
     * @param book
     * @return
     */
    @Override
    public Integer update(Book book) {
        String sql = "update t_book set book_name = ?,author=?,price=?,sales=?,stock=?,img_path=? where book_id=?";

        return update(sql, book.getBookName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getBookId());
    }

    /**
     * 根据id删除一天数据  在后台系统
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        String sql = "delete from t_book where book_id = ?";
        return update(sql, id);
    }

    /**
     * 根据id 查询对应的书籍
     * @param bookId
     * @return
     */
    @Override
    public Book getBookById(String bookId) {
        return selectBookInfo(bookId);
    }
}
