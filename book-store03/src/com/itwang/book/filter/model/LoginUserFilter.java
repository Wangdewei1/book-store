package com.itwang.book.filter.model;

import com.itwang.book.entity.User;
import com.itwang.book.filter.base.MyFilter;
import com.itwang.book.utils.BookConst;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginUserFilter extends MyFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(BookConst.LOGIN_ATTR_USER_VALUE);

        if (user == null){
            session.setAttribute("message", "请登入后再操作！");
            request.getRequestDispatcher("/UserServlet?method=toLoginPage").forward(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
