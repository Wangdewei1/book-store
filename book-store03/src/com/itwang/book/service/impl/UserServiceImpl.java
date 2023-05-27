package com.itwang.book.service.impl;

import com.itwang.book.dao.model.api.UserDao;
import com.itwang.book.dao.model.impl.UserDaoImpl;
import com.itwang.book.entity.User;
import com.itwang.book.service.api.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    /**
     * 用户名密码  用户登入
     * @param username
     * @param password
     * @return
     */
    @Override
    public User userLogin(String username, String password) {
        return userDao.userLoginByUsernamePwd(username,password);
    }

    /**
     * 注册用户信息
     * @param username
     * @param password
     * @param email
     * @return
     */
    @Override
    public User userRegister(String username, String password, String email) {
        User user = new User();
        user.setUserName(username);
        user.setUserPwd(password);
        user.setEmail(email);
        return userDao.userRegisterInfo(user);
    }

    /**
     * 查询用户名数量
     * @param username
     * @return
     */
    @Override
    public Integer isUsername(String username) {
        return userDao.isUsername(username);
    }
}
