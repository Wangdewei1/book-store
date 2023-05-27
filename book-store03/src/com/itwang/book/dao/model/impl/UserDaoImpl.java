package com.itwang.book.dao.model.impl;

import com.itwang.book.dao.base.impl.BaseDaoImpl;
import com.itwang.book.dao.model.api.UserDao;
import com.itwang.book.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    /**
     * 根据用户名，密码，t_user表查询数据 返回user用户对象
     * @param username
     * @param password
     * @return
     */
    @Override
    public User userLoginByUsernamePwd(String username, String password) {
        String sql = "select user_id userId , user_name userName , user_pwd userPwd , email from t_user where user_name = ? and user_pwd = ?";
        return selectOne(sql, User.class, username,password);
    }

    /**
     * 新增用户信息
     * @return
     */
    @Override
    public User userRegisterInfo(User user) {
        String sql = "insert into t_user(user_name,user_pwd,email) values(?,?,?)";
        Integer rows = update(sql, user.getUserName(), user.getUserPwd(), user.getEmail());
        if (rows > 0){
            return user;
        }
        return null;
    }

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    @Override
    public Integer isUsername(String username) {
        String sql = "select count(*) from t_user where user_name = ?";
        Integer count = Integer.valueOf(Integer.parseInt(selectScalar(sql, username).toString()));
        return count;
    }
}
