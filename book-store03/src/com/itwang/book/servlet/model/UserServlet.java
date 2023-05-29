package com.itwang.book.servlet.model;

import com.google.gson.Gson;
import com.itwang.book.entity.User;
import com.itwang.book.service.api.UserService;
import com.itwang.book.service.impl.UserServiceImpl;
import com.itwang.book.servlet.base.MethodServlet;
import com.itwang.book.utils.MD5Utils;
import com.itwang.book.utils.ResultEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends MethodServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 到登录页
     */
    protected void toLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("user/login", req, resp);
    }

    /**
     * 登入操作
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = MD5Utils.getMD5Utils(req.getParameter("password").replaceAll("==", ""));
        User user = userService.userLogin(username, password);
        if (null == user) {
            req.setAttribute("msg", "用户名或密码不正确，登陆失败！");
//            resp.sendRedirect(req.getContextPath() + "/UserServlet?method=toLoginPage");
            processTemplate("user/login", req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("userLogin", user);
            //以秒为单位
            session.setMaxInactiveInterval(60*30);

            processTemplate("user/login_success", req, resp);
        }
    }

    /**
     * 到注册页
     */
    protected void toRegisterPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("user/regist", req, resp);
    }

    /**
     * 注销登录
     */
    protected void clearLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("userLogin");
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }

    /**
     * 注册操作
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = MD5Utils.getMD5Utils(req.getParameter("password"));
        String email = req.getParameter("email");
        String codeForm = req.getParameter("code");

        HttpSession session = req.getSession();
        String codeSession = (String) session.getAttribute("KAPTCHA_SESSION_KEY");

        System.out.println("codeSession = " + codeSession);
        if (codeForm == null || codeForm.trim().length() == 0){
            req.setAttribute("msg", "注册失败，验证码不可以为空");
            processTemplate("user/regist", req, resp);
        }

        if (!codeSession.equals(codeForm)){
            req.setAttribute("msg", "注册失败，验证码不正确！");
            processTemplate("user/regist", req, resp);
        }

        User user = userService.userRegister(username,password,email);

        if (user == null){
            req.setAttribute("msg", "注册失败，请仔细核对注册信息！");
            processTemplate("user/regist", req, resp);
        }

        session.setAttribute("user", user);
//        req.setAttribute("user", user);
        processTemplate("user/regist_success",req,resp);
    }

    /**
     * 注销注册
     */
    protected void clearRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }

    /**
     * 值改变查询数据库是否有重复的用户名
     */
    protected void getUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");

        String username = req.getParameter("username");

        System.out.println("username = " + username);

        Integer count = userService.isUsername(username);

//        if (count > 0){
//            req.getSession().setAttribute("msg", "用户名不可用");
//            resp.sendRedirect(req.getContextPath() + "/UserServlet?method=toRegisterPage");
//        }
//        if (count == 0){
//            req.getSession().setAttribute("msg", "用户名可用");
//        }

        ResultEntity<Void> resultEntity = (count == 0) ? ResultEntity.ok() : ResultEntity.failed("用户名不可用");

        Gson gson = new Gson();

        String toJson = gson.toJson(resultEntity);

        resp.setContentType("application/json;charset=UTF-8");

        resp.getWriter().write(toJson);

    }
}
