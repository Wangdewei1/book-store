package com.itwang.book.servlet.model;

import com.itwang.book.entity.Cart;
import com.itwang.book.entity.Order;
import com.itwang.book.entity.OrderItem;
import com.itwang.book.entity.User;
import com.itwang.book.service.api.OrderItemService;
import com.itwang.book.service.api.OrderService;
import com.itwang.book.service.impl.OrderItemServiceImpl;
import com.itwang.book.service.impl.OrderServiceImpl;
import com.itwang.book.servlet.base.MethodServlet;
import com.itwang.book.utils.BookConst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends MethodServlet {
    private OrderService orderService = new OrderServiceImpl();

    private OrderItemService orderItemService = new OrderItemServiceImpl();
    /**
     *  获取所有订单
     */
    protected void getAllOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(BookConst.LOGIN_ATTR_USER_VALUE);
        List<Order> orderList = orderService.getOrderList(user.getUserId());

        if (orderList == null || orderList.size() ==0){
            req.getSession().setAttribute("message", "您还没有下单！");
//            req.getRequestDispatcher("/UserServlet?method=toLoginPage")
        }

        if (orderList != null || orderList.size() > 0){
//            req.getSession().setAttribute("orderList", orderList);
            req.setAttribute("orderList", orderList);
            processTemplate("order/order", req, resp);
        }
    }

    /**
     *  checkOrder  核查订单
     */
    protected void checkOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.
        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute(Cart.CART_ATTR_NAME);

        User user = (User) session.getAttribute(BookConst.LOGIN_ATTR_USER_VALUE);

        String orderSequence = orderService.checkOrder(cart,user);

        session.setAttribute("orderSequence", orderSequence);

        processTemplate("cart/checkout", req, resp);

        //保证每次下单的订单号都不一样，解决session一段时间的域值问题
//        session.removeAttribute("orderSequence");
    }

    /**
     * 订单查看详情
     */
    protected void getAllOrderItemInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(BookConst.LOGIN_ATTR_USER_VALUE);
        Integer userId = user.getUserId();
        String orderSequence = (String) req.getSession().getAttribute("orderSequence");
        List<OrderItem> orderItemList = orderItemService.getAllOrderItemInfo(req,userId,orderSequence);

        if (orderItemList == null || orderItemList.size() == 0 || orderSequence == null){
            req.getSession().setAttribute("message", "没有订单数据");
            throw new RuntimeException("订单异常,请添加购物车");
        }

        req.setAttribute("orderItemList",orderItemList);
        processTemplate("order/order_info", req, resp);
//        req.getRequestDispatcher("/OrderServlet?method=getAllOrderItemInfo").forward(req, resp);
    }
}
