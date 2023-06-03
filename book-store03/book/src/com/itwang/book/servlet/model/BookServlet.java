package com.itwang.book.servlet.model;

import com.itwang.book.entity.Book;
import com.itwang.book.service.api.BookService;
import com.itwang.book.service.impl.BookServiceImpl;
import com.itwang.book.servlet.base.MethodServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends MethodServlet {
    private BookService bookService = new BookServiceImpl();
    /**
     * 到后台管理 测试没有数据的情况
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void toBookPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("manager/book_manager", req, resp);
    }

    /**
     * 查询后台图书列表
     */
    protected void getBookList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = bookService.getBookList();

        req.setAttribute("bookList", bookList);

        processTemplate("manager/book_manager",req,resp);
    }

    /**
     * 到添加页
     */
    protected void toAddPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("manager/book_add", req, resp);
    }

    /**
     * 添加一本后台图书
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        String author = req.getParameter("author");
        String price = req.getParameter("price");
        String sales = req.getParameter("sales");
        String stock = req.getParameter("stock");

        Book book = new Book(null,bookName,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock),"static/uploads/huozhe.jpg");

        Integer rows = bookService.addBook(book);

        if (rows > 0){
            req.setAttribute("book", book);
            resp.sendRedirect(req.getContextPath() + "/BookServlet?method=getBookList");
        } else {
            req.setAttribute("msg", "添加失败，请仔细检查！");
        }
    }

    /**
     * 到修改页
     */
    protected void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("manager/book_edit", req, resp);
    }

    /**
     * 回显数据
     */
    protected void selectBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.selectBookInfo(id);

        if (null != book){
            req.setAttribute("book", book);
            processTemplate("manager/book_edit",req,resp);
        } else {
            req.setAttribute("msg", "修改失败，请仔细检查！");
            processTemplate("manager/book_manager",req,resp);
        }
    }

    /**
     * 更新数据
     */
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String bookName = req.getParameter("bookName");
        String author = req.getParameter("author");
        String price = req.getParameter("price");
        String sales = req.getParameter("sales");
        String stock = req.getParameter("stock");

        Book book = new Book(Integer.parseInt(id),bookName,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock),"static/uploads/pinang.jpg");

        Integer rows = bookService.update(book);

        if (rows > 0){
            req.setAttribute("book", book);
            resp.sendRedirect(req.getContextPath() + "/BookServlet?method=getBookList");
//            processTemplate("manager/book_manager", req, resp);
        }
    }

    /**
     * 根据删除一本书
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Integer rows = bookService.deleteById(id);

        if (rows > 0){
            req.setAttribute("id", id);
            resp.sendRedirect(req.getContextPath() + "/BookServlet?method=getBookList");
        }
    }
}
