package com.itwang.book;

import com.itwang.book.dao.base.api.BaseDao;
import com.itwang.book.dao.base.impl.BaseDaoImpl;
import com.itwang.book.entity.User;
import com.itwang.book.utils.MD5Utils;
import org.junit.Test;

public class TestRegister {
    @Test
    public void TestRegisterInfo(){
        BaseDao<User> baseDao = new BaseDaoImpl<>();

        String sql = "insert into t_user(user_name,user_pwd,email) values(?,?,?)";

        String password = MD5Utils.getMD5Utils("12345678").replaceAll("==", "");
        baseDao.update(sql, "不问懂得小于",password,"12341@163.com");
    }
}
