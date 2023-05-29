package com.itwang.book.service.api;

import com.itwang.book.entity.OrderItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderItemService {
    List<OrderItem> getAllOrderItemInfo(HttpServletRequest req,Integer userId,String orderSequence);
}
