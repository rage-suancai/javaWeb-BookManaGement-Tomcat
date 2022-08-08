package com.book.servlet.manage;

import com.book.Utils.ThymeleafUtil;
import com.book.service.BookService;
import com.book.service.BorrowService;
import com.book.service.StudentService;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.BorrowServiceImpl;
import com.book.service.impl.StudentServiceImpl;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-borrow")
public class AddBorrowServlet extends HttpServlet {

    BookService service1;
    StudentService service2;
    BorrowService service3;
    @Override
    public void init() throws ServletException {
        service1 = new BookServiceImpl();
        service2 = new StudentServiceImpl();
        service3 = new BorrowServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        Context context = new Context();
        context.setVariable("book_list", service1.getActiveBookList());
        context.setVariable("student_list", service2.getStudentList());
        ThymeleafUtil.process("add-borrow.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("student"));
        int bid = Integer.parseInt(req.getParameter("book"));
        service3.addBorrow(sid, bid);
        resp.sendRedirect("add-borrow");
    }

}
