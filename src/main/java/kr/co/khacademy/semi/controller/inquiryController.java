package kr.co.khacademy.semi.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.khacademy.semi.dao.InquiryDao;
import kr.co.khacademy.semi.model.Inquiry;

@WebServlet("/admin/inquiry/*")
public class inquiryController extends HttpServlet {
    
    private static final InquiryDao inquiryDao = InquiryDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                resp.sendRedirect("/WEB-INF/views/admin/inquiry/register.jsp");
            } else if ("/list".equals(pathInfo)) {
                List<Inquiry> inquiries = inquiryDao.read();
                req.setAttribute("inquiries", inquiries);
                req.getRequestDispatcher("/WEB-INF/views/admin/inquiry/list.jsp").forward(req, resp);
            } else if ("/item".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                Inquiry inquiry = inquiryDao.read(id);
                req.setAttribute("inquiry", inquiry);
                req.getRequestDispatcher("/WEB-INF/views/admin/inquiry/item.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                Inquiry inquiry = Inquiry.of(req);
                inquiryDao.create(inquiry);
                resp.sendRedirect("/admin/inquiry/list");
            } else if ("/modify".equals(pathInfo)) {
                Inquiry inquiry = Inquiry.of(req);
                inquiryDao.update(inquiry);
                String location = String.format("/admin/inquiry/item?id=%d", inquiry.getId());
                resp.sendRedirect(location);
            } else if ("/delete".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                inquiryDao.delete(id);
                resp.sendRedirect("/admin/inquiry/list");
            }
        }catch (SQLException e) {
            resp.sendRedirect("/error");
        }
    }


}
