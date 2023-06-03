package com.itwang.book.service.api;

import com.itwang.book.entity.User;

public interface UserService {
    User userLogin(String username, String password);

    User userRegister(String username, String password, String email);

    Integer isUsername(String username);
}
