package com.book.servlet.manage;

import com.book.service.BorrowService;
import com.book.service.impl.BorrowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/return-book")
public class ReturnBorrowServlet extends HttpServlet {

    BorrowService service;
    @Override
    public void init() throws ServletException {
        service = new BorrowServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset-UTF-8");

        String id = req.getParameter("id");
        service.returnBorrow(id);
        resp.sendRedirect("index");
    }

}
