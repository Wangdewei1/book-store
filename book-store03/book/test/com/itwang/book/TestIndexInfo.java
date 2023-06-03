package com.itwang.book;

import com.itwang.book.dao.base.api.BaseDao;
import com.itwang.book.dao.model.impl.BookDaoImpl;
import com.itwang.book.entity.Book;
import org.junit.Test;

import java.util.List;

public class TestIndexInfo {
    @Test
    public void TestIndexInfoList(){
        BaseDao<Book> baseDao = new BookDaoImpl();

        String sql = "select book_id bookId , book_name bookName,author,price,sales,stock,img_path imgPath from t_book";

        List<Book> books = baseDao.selectAllData(sql, Book.class);

        System.out.println("books = " + books);
    }
}
