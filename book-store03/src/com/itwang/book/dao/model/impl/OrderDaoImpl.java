package com.itwang.book.dao.model.impl;

import com.itwang.book.dao.base.impl.BaseDaoImpl;
import com.itwang.book.dao.model.api.OrderDao;
import com.itwang.book.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {
    /**
     * 查询数据库中的订单数据
     * @return
     */
    @Override
    public List<Order> getOrderList(Integer userId) {
//        String sql = "select order_id,order_sequence,create_time,total_count,order_status from t_order where user_id = ?";
        String sql = "select order_id orderId,order_sequence orderSequence,create_time createTime,total_count totalCount,total_amount totalAmount from t_order where user_id = ?";
        return selectAllData(sql, Order.class, userId);
    }

    /**
     * 插入订单
     * @param order
     * @return
     */
    @Override
    public Integer insertOrder(Order order) {
        String sql = "insert into t_order(order_sequence,create_time,total_count,total_amount,order_status,user_id) values(?,?,?,?,?,?)";

        return generateGetPrimaryKey(sql,
                order.getOrderSequence(),
                order.getCreateTime(),
                order.getTotalCount(),
                order.getTotalAmount(),
                order.getOrderStatus(),
                order.getUserId());
    }

    /**
     * 根据用户id,订单号 查询 订单id
     * @param req
     * @param userId
     * @return
     */
    @Override
    public Integer selectOrderSequence(HttpServletRequest req, Integer userId,String orderSequence) {
        String sql = "select order_id from t_order where user_id = ? and order_sequence = ?";
        Object o = selectScalar(sql, userId,orderSequence);
        return Integer.valueOf(o.toString());
    }
}
