package com.book.servlet.pages;

import com.book.Utils.ThymeleafUtil;
import com.book.entity.User;
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

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    BookService service1;
    BorrowService service2;
    StudentService service3;
    @Override
    public void init() throws ServletException {
        service1 = new BookServiceImpl();
        service2 = new BorrowServiceImpl();
        service3 = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNickname());
        context.setVariable("borrow_list", service2.getBorrowList());
        context.setVariable("book_count", service1.getBookList().size());
        context.setVariable("student_count", service3.getStudentList().size());
        ThymeleafUtil.process("index.html", context, resp.getWriter());
    }

}
