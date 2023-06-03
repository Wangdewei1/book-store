package com.itwang.book;

import com.itwang.book.dao.base.api.BaseDao;
import com.itwang.book.dao.base.impl.BaseDaoImpl;
import com.itwang.book.entity.Order;
import org.junit.Test;

import java.util.List;

public class TestOrderInfoByUserId {
    @Test
    public void TestOrderByUserId(){

        BaseDao<Order> baseDao = new BaseDaoImpl<>();
        Integer id= 14;
        String sql = "select order_id orderId,order_sequence orderSequence,create_time createTime,total_count totalCount,order_status orderStatus from t_order where user_id = ?";
        List<Order> orderList = baseDao.selectAllData(sql, Order.class, id);

        System.out.println("orderList = " + orderList);


    }
}
