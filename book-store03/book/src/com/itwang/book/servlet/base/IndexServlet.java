package com.itwang.book.servlet.base;

import com.itwang.book.entity.Book;
import com.itwang.book.service.api.BookService;
import com.itwang.book.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends ViewBaseServlet {
    private BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        List<Book> bookList = bookService.getIndexBookList();

//        System.out.println(bookList);

        req.setAttribute("bookList", bookList);

        processTemplate("index", req, resp);
    }
}
