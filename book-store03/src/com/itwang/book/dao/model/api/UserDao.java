package com.itwang.book.dao.model.api;

import com.itwang.book.entity.User;

public interface UserDao {
    User userLoginByUsernamePwd(String username, String password);

    User userRegisterInfo(User user);

    Integer isUsername(String username);
}
