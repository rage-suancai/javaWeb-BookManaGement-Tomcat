package com.book.servlet.auth;

import com.book.Utils.ThymeleafUtil;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService service;
    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // 处理请求(可见)
        resp.setContentType("text/html;charset=UTF-8");

        Cookie[] cookies = req.getCookies();
        if (cookies != null){
            String username = null;
            String password = null;
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("username")) username = cookie.getValue();
                if (cookie.getName().equals("password")) password = cookie.getValue();
            }
            if (username != null && password != null) {
                if (service.auth(username, password, req.getSession())){
                    resp.sendRedirect("index");
                    return;
                }
            }
        }

        Context context = new Context();
        if (req.getSession().getAttribute("login-failure") != null){
           context.setVariable("failure", true);
           req.getSession().removeAttribute("login-failure");
        }
        /*if (req.getSession().getAttribute("user") != null){
            resp.sendRedirect("index");
            return;
        }*/
        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // 处理请求(后台)
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");
        if (service.auth(username, password, req.getSession())){
            if (remember != null){
                Cookie cookie_username = new Cookie("username", username);
                cookie_username.setMaxAge(60 * 60 * 24 * 7);
                Cookie cookie_password = new Cookie("password", password);
                cookie_password.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(cookie_username);
                resp.addCookie(cookie_password);
            }
            resp.sendRedirect("index");
        } else {
            req.getSession().setAttribute("login-failure", new Object());
            this.doGet(req, resp);
        }

    }

}
