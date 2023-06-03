package com.itwang.book.dao.model.api;

import com.itwang.book.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderDao {
    List<Order> getOrderList(Integer userId);

    Integer insertOrder(Order order);

    Integer selectOrderSequence(HttpServletRequest req, Integer userId,String orderSequence);
}
