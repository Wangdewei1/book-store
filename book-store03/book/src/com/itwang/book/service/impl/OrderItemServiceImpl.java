package com.itwang.book.service.impl;

import com.itwang.book.dao.model.api.OrderDao;
import com.itwang.book.dao.model.api.OrderItemDao;
import com.itwang.book.dao.model.impl.OrderDaoImpl;
import com.itwang.book.dao.model.impl.OrderItemDaoImpl;
import com.itwang.book.entity.Order;
import com.itwang.book.entity.OrderItem;
import com.itwang.book.service.api.OrderItemService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderDao orderDao = new OrderDaoImpl();

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    /**
     * 查看订单中的数据
     * @param req
     * @return
     */
    @Override
    public List<OrderItem> getAllOrderItemInfo(HttpServletRequest req, Integer userId,String orderSequence) {

        Integer orderId = orderDao.selectOrderSequence(req,userId,orderSequence);

        return orderItemDao.getAllOrderItemInfo(orderId);
    }
}
