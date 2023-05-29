package com.itwang.book.dao.model.api;

import com.itwang.book.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    void batchInsert(List<OrderItem> orderItemList);

    List<OrderItem> getAllOrderItemInfo(Integer orderId);
}
