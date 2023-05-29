package com.itwang.book.service.api;

import com.itwang.book.entity.Cart;
import com.itwang.book.entity.Order;
import com.itwang.book.entity.User;

import java.util.List;

public interface OrderService {
    List<Order> getOrderList(Integer userId);

    String checkOrder(Cart cart, User user);
}
