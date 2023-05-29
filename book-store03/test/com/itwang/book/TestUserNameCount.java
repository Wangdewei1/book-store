package com.itwang.book;

import com.itwang.book.dao.base.api.BaseDao;
import com.itwang.book.dao.base.impl.BaseDaoImpl;
import com.itwang.book.entity.Order;
import com.itwang.book.entity.User;
import org.junit.Test;

public class TestUserNameCount {
    @Test
    public void TestUserNameCountByUsername(){
        BaseDao<User> baseDao = new BaseDaoImpl<>();
        String username = "zhangsan123412341234";
        String sql = "select count(*) from t_user where user_name = ?";
        Object o = baseDao.selectScalar(sql, username);
        Integer count = Integer.valueOf(Integer.parseInt(o.toString()));
        System.out.println("o = " + count);
    }

    @Test
    public void Test1(){
        BaseDao<Order> baseDao = new BaseDaoImpl<>();

        String sql = "select order_id from t_order where user_id = ? and order_sequence = ?";
        Object o = baseDao.selectScalar(sql, "14","2023053001100966914");

        System.out.println(Integer.valueOf(o.toString()));
    }
}
