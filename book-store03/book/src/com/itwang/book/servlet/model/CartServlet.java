package com.itwang.book.servlet.model;

import com.google.gson.Gson;
import com.itwang.book.entity.Book;
import com.itwang.book.entity.Cart;
import com.itwang.book.service.api.BookService;
import com.itwang.book.service.impl.BookServiceImpl;
import com.itwang.book.servlet.base.MethodServlet;
import com.itwang.book.utils.ResultEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends MethodServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 到购物车页
     */
    protected void toCartPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("cart/cart", req, resp);
    }

    /**
     * 添加购物车
     */
    protected void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute(Cart.CART_ATTR_NAME);

        if (cart == null){
            cart = new Cart();
            session.setAttribute(Cart.CART_ATTR_NAME, cart);
        }

        String bookId = req.getParameter("bookId");

        Book book = bookService.getBookById(bookId);

        Integer count = 1;
        cart.addCart(book, count);

        ResultEntity<Integer> resultEntity = ResultEntity.ok(cart.getTotalCount());

        generateAjaxResponse(resp, resultEntity);

    }

    /**
     * 获取购物车列表
     */
    protected void getAllCartItemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute(Cart.CART_ATTR_NAME);

        if (cart != null){
            Map<String,Object> cartMap = new HashMap<>();
            cartMap.put("totalCount", cart.getTotalCount());
            cartMap.put("totalAmount", cart.getTotalAmount());
            cartMap.put("cartItemList", cart.getCartItemList());

            ResultEntity<Map<String,Object>> resultEntity = ResultEntity.ok(cartMap);
            generateAjaxResponse(resp, resultEntity);
        } else {
            ResultEntity<Void> resultEntity = ResultEntity.failed("购物车为空，请添加商品！");
            generateAjaxResponse(resp, resultEntity);
        }
    }


    /**
     * 更新购物车的数量
     */
    protected void updateCartItemCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute(Cart.CART_ATTR_NAME);

        if (cart != null){
            String bookId = req.getParameter("bookId");
            String count = req.getParameter("count");

            cart.updateCartItemCount(Integer.parseInt(bookId), Integer.parseInt(count));

            ResultEntity<Integer> resultEntity = ResultEntity.ok(cart.getTotalCount());

            generateAjaxResponse(resp, resultEntity);
        } else {
            ResultEntity<Object> resultEntity = ResultEntity.failed("没有获取到购物车的数量，请刷新页面！");
            generateAjaxResponse(resp, resultEntity);
        }
    }

    /**
     * 编辑购物车中每个条目中的商品数量
     */
/*    protected void editCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        String count = req.getParameter("count");
    }*/

    /**
     * 清除购物车
     */
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute(Cart.CART_ATTR_NAME);

        cart.clearCart();

        ResultEntity<Integer> ok = ResultEntity.ok(cart.getTotalCount());

        generateAjaxResponse(resp, ok);
    }


    /**
     * 到结账页
     */
    protected void toCheckOutPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("cart/checkout", req, resp);
    }

    /**
     * 抽离额外的方法  确定ajax响应的信息
     * @param resp
     * @param resultEntity
     * @param <T>
     * @throws IOException
     */
    private static <T> void generateAjaxResponse(HttpServletResponse resp, ResultEntity<T> resultEntity) throws IOException {
        Gson gson = new Gson();

        String toJson = gson.toJson(resultEntity);

        resp.setContentType("application/json;charset=UTF-8");

        resp.getWriter().write(toJson);
    }
}
