package com.itwang.book;

import com.itwang.book.dao.base.api.BaseDao;
import com.itwang.book.dao.base.impl.BaseDaoImpl;
import com.itwang.book.entity.User;
import org.junit.Test;

public class TestLogin {
    @Test
    public void TestLogin(){
        BaseDao<User> userBaseDao = new BaseDaoImpl<>();

        String sql = "select user_id userId , user_name userName , user_pwd userPwd , email from t_user where user_name = 'wangziwei' and user_pwd = 123456";

        User user = userBaseDao.selectOne(sql, User.class);

        System.out.println(user);
    }
}
